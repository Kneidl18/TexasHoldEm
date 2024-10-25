import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class Main {
    public static void main(String[] args) {
        Card card1 = new Card(Suit.SPADE, Rank.ACE);
        Card card2 = new Card(Suit.HEART, Rank.TWO);
        Card card3 = new Card(Suit.DIAMOND, Rank.THREE);
        Card card4 = new Card(Suit.CLUB, Rank.FOUR);
        Card card5 = new Card(Suit.SPADE, Rank.FIVE);

        Hand hand = new Hand(new Card[] {card1, card2, card3, card4, card5});
        System.out.println(hand);
        System.out.println("===============>   " + hand.getHandValue());

        hand= new Hand("S6    H2 D3  C4     S5");
        System.out.println(hand);
        System.out.println("===============>   " + hand.getHandValue());

        hand= new Hand("SK S5 HK DK CK");
        System.out.println(hand);
        System.out.println("===============>   " + hand.getHandValue());

        hand= new Hand("S4 S8 S2 SK S3");
        System.out.println(hand);
        System.out.println("===============>   " + hand.getHandValue());

        hand= new Hand("S6 H3 CA H8 C6");
        System.out.println(hand);
        System.out.println("===============>   " + hand.getHandValue());



    }

    private static Stream<TestCase> provideTestCases() {
        return Stream.of(
                new TestCase("Royal Flush", new Card[]{
                        new Card(Suit.HEART, Rank.TEN),
                        new Card(Suit.HEART, Rank.JACK),
                        new Card(Suit.HEART, Rank.QUEEN),
                        new Card(Suit.HEART, Rank.KING),
                        new Card(Suit.HEART, Rank.ACE)
                }, HandVal.ROYAL_FLUSH),

                new TestCase("Straight Flush", new Card[]{
                        new Card(Suit.CLUB, Rank.FIVE),
                        new Card(Suit.CLUB, Rank.SIX),
                        new Card(Suit.CLUB, Rank.SEVEN),
                        new Card(Suit.CLUB, Rank.EIGHT),
                        new Card(Suit.CLUB, Rank.NINE)
                }, HandVal.STRAIGHT_FLUSH),

                new TestCase("Four of a Kind", new Card[]{
                        new Card(Suit.SPADE, Rank.NINE),
                        new Card(Suit.HEART, Rank.NINE),
                        new Card(Suit.DIAMOND, Rank.NINE),
                        new Card(Suit.CLUB, Rank.NINE),
                        new Card(Suit.HEART, Rank.KING)
                }, HandVal.FOUR_OF_A_KIND),

                new TestCase("Full House", new Card[]{
                        new Card(Suit.SPADE, Rank.THREE),
                        new Card(Suit.HEART, Rank.THREE),
                        new Card(Suit.DIAMOND, Rank.THREE),
                        new Card(Suit.CLUB, Rank.KING),
                        new Card(Suit.SPADE, Rank.KING)
                }, HandVal.FULL_HOUSE),

                new TestCase("Flush", new Card[]{
                        new Card(Suit.CLUB, Rank.TWO),
                        new Card(Suit.CLUB, Rank.FIVE),
                        new Card(Suit.CLUB, Rank.EIGHT),
                        new Card(Suit.CLUB, Rank.JACK),
                        new Card(Suit.CLUB, Rank.KING)
                }, HandVal.FLUSH),

                new TestCase("Straight", new Card[]{
                        new Card(Suit.DIAMOND, Rank.FOUR),
                        new Card(Suit.CLUB, Rank.FIVE),
                        new Card(Suit.SPADE, Rank.SIX),
                        new Card(Suit.HEART, Rank.SEVEN),
                        new Card(Suit.DIAMOND, Rank.EIGHT)
                }, HandVal.STRAIGHT),

                new TestCase("Three of a Kind", new Card[]{
                        new Card(Suit.SPADE, Rank.JACK),
                        new Card(Suit.HEART, Rank.JACK),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.CLUB, Rank.TWO),
                        new Card(Suit.HEART, Rank.KING)
                }, HandVal.THREE_OF_A_KIND),

                new TestCase("Two Pair", new Card[]{
                        new Card(Suit.SPADE, Rank.FIVE),
                        new Card(Suit.HEART, Rank.FIVE),
                        new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.CLUB, Rank.KING),
                        new Card(Suit.HEART, Rank.THREE)
                }, HandVal.TWO_PAIRS),

                new TestCase("One Pair", new Card[]{
                        new Card(Suit.SPADE, Rank.TWO),
                        new Card(Suit.HEART, Rank.TWO),
                        new Card(Suit.DIAMOND, Rank.KING),
                        new Card(Suit.CLUB, Rank.JACK),
                        new Card(Suit.HEART, Rank.FOUR)
                }, HandVal.PAIR),

                new TestCase("High Card", new Card[]{
                        new Card(Suit.SPADE, Rank.TWO),
                        new Card(Suit.HEART, Rank.FIVE),
                        new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.CLUB, Rank.JACK),
                        new Card(Suit.HEART, Rank.KING)
                }, HandVal.HIGH_CARD)
        );
    }

    // Define parameterized test
    @ParameterizedTest
    @MethodSource("provideTestCases")
    @DisplayName("Test Hand Evaluation")
    void testHandEvaluation(TestCase testCase) {
        Hand hand = new Hand(testCase.cards);
        assertEquals(testCase.expectedHandVal, hand.getHandValue(),
                "Failed for: " + testCase.description);
    }

    // Inner class to define a test case structure
    static class TestCase {
        String description;
        Card[] cards;
        HandVal expectedHandVal;

        TestCase(String description, Card[] cards, HandVal expectedHandVal) {
            this.description = description;
            this.cards = cards;
            this.expectedHandVal = expectedHandVal;
        }
    }
}