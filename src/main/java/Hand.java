package main.java;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Hand {
    private Card[] cards;
    private HandVal handValue;

    public Hand(Card[] cards) {
        this.cards = cards;
        this.handValue = calcHandValue();
    }

    // constructor that takes a string describing 5 cards,
    // each with two characters, separated by at least one space
    // eg, "D2 HA ST CK S2"
    public Hand(String handDesc) {
        String[] cardDescs = handDesc.split("\\s+");
        if (cardDescs.length != 5) {
            throw new IllegalArgumentException("Invalid hand description: " + handDesc);
        }
        cards = new Card[5];
        for (int i = 0; i < 5; i++) {
            cards[i] = new Card(cardDescs[i]);
        }
        this.handValue = calcHandValue();
    }

    private HandVal calcHandValue() {
        // Sort the cards by rank for easier analysis
        Arrays.sort(cards, Comparator.comparing(Card::getRank));

        // Initialize helper variables
        boolean isFlush = true;
        boolean isStraight = true;
        HashMap<Rank, Integer> rankCounts = new HashMap<>();

        // Check for flush
        Suit firstSuit = cards[0].getSuit();
        for (Card card : cards) {
            if (card.getSuit() != firstSuit) {
                isFlush = false;
                break;
            }
        }

        // Count ranks and check for straight
        for (int i = 0; i < cards.length; i++) {
            rankCounts.put(cards[i].getRank(), 1 + rankCounts.getOrDefault(cards[i].getRank(),
                    0));

            if (i > 0 && cards[i].getRank().ordinal() - cards[i - 1].getRank().ordinal() != 1) {
                // Special case for Ace-low straight (A-2-3-4-5)
                if (!(i == 4 && cards[0].getRank() == Rank.ACE && cards[4].getRank() == Rank.FIVE)) {
                    isStraight = false;
                }
            }
        }

        // Determine hand based on ranks and counts
        if (isFlush && isStraight && cards[0].getRank() == Rank.TEN && cards[4].getRank() == Rank.ACE) {
            return HandVal.ROYAL_FLUSH;
        }

        if (isFlush && isStraight) {
            return HandVal.STRAIGHT_FLUSH;
        }

        if (rankCounts.containsValue(4)) {
            return HandVal.FOUR_OF_A_KIND;
        }

        if (rankCounts.containsValue(3) && rankCounts.containsValue(2)) {
            return HandVal.FULL_HOUSE;
        }

        if (isFlush) {
            return HandVal.FLUSH;
        }

        if (isStraight) {
            return HandVal.STRAIGHT;
        }

        if (rankCounts.containsValue(3)) {
            return HandVal.THREE_OF_A_KIND;
        }

        int pairs = 0;
        for (int count : rankCounts.values()) {
            if (count == 2) pairs++;
        }

        if (pairs == 2) {
            return HandVal.TWO_PAIRS;
        }

        if (pairs == 1) {
            return HandVal.PAIR;
        }

        return HandVal.HIGH_CARD;
    }

    public Card[] getCards() {
        return cards;
    }

    public HandVal getHandValue() {
        return handValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card).append("\n");  // new line after each card
        }
        return sb.toString().trim();
    }
}
