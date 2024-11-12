import java.util.Arrays;
import java.util.Comparator;

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
        // sort the cards by rank
        Arrays.sort(cards, Comparator.comparing(Card::getRank));

        //  Check for straight
        boolean isStraight = isStraight();

        // check for full house
        boolean isFullHouse = false;
        if (cards[0].getRank().getValue() == cards[1].getRank().getValue() &&
                cards[2].getRank().getValue() == cards[3].getRank().getValue() &&
                cards[2].getRank().getValue() == cards[4].getRank().getValue()) {
            isFullHouse = true;
        } else if (cards[0].getRank().getValue() == cards[1].getRank().getValue() &&
                cards[0].getRank().getValue() == cards[2].getRank().getValue() &&
                cards[3].getRank().getValue() == cards[4].getRank().getValue()) {
            isFullHouse = true;
        }
        if (isFullHouse) {
            return HandVal.FULL_HOUSE;
        }

        //  Check for flush
        boolean isFlush = Arrays.stream(cards).allMatch(card -> card.getSuit() == cards[0].getSuit());
        if (isFlush && !isStraight) {
            return HandVal.FLUSH;
        }

        //  Check for royal flush
        if (isFlush && isStraight && cards[4].getRank() == Rank.ACE) {
            return HandVal.ROYAL_FLUSH;
        }

        //  Check for straight flush
        if (isFlush && isStraight) {
            return HandVal.STRAIGHT_FLUSH;
        }

        if (isStraight) {
            return HandVal.STRAIGHT;
        }

        // check for poker
        boolean isPoker = false;
        for (int i = 0; i < cards.length - 3; i++) {
            if (cards[i].getRank().getValue() == cards[i + 1].getRank().getValue() &&
                    cards[i].getRank().getValue() == cards[i + 2].getRank().getValue() &&
                    cards[i].getRank().getValue() == cards[i + 3].getRank().getValue()) {
                isPoker = true;
                break;
            }
        }
        if (isPoker) {
            return HandVal.POKER;
        }

        // check for three of a kind
        boolean isThreeOfAKind = false;
        for (int i = 0; i < cards.length - 2; i++) {
            if (cards[i].getRank().getValue() == cards[i + 1].getRank().getValue() &&
                    cards[i].getRank().getValue() == cards[i + 2].getRank().getValue()) {
                isThreeOfAKind = true;
                break;
            }
        }
        if (isThreeOfAKind) {
            return HandVal.THREE_OF_A_KIND;
        }

        // check for one or two pairs
        int pairCount = 0;
        for (int i = 0; i < cards.length - 1; i++) {
            if (cards[i].getRank().getValue() == cards[i + 1].getRank().getValue()) {
                pairCount++;
                i++;  // skip the next card
            }
        }
        if (pairCount == 2) {
            return HandVal.TWO_PAIRS;
        }
        if (pairCount == 1) {
            return HandVal.ONE_PAIR;
        }

        // otherwise ...
        return HandVal.HIGH_CARD;
    }

    private boolean isStraight() {
        boolean isStraight = true;
        for (int i = 0; i < cards.length - 1; i++) {
            if (cards[i].getRank().getValue() != cards[i + 1].getRank().getValue() - 1) {
                isStraight = false;
                break;
            }
        }
        // Special case for Ace-low straight (A-2-3-4-5)
        if (!isStraight && cards[0].getRank() == Rank.TWO && cards[1].getRank() == Rank.THREE &&
                cards[2].getRank() == Rank.FOUR && cards[3].getRank() == Rank.FIVE && cards[4].getRank() == Rank.ACE) {
            isStraight = true;
        }
        return isStraight;
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
