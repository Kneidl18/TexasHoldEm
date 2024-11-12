import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {
    @Test
    void getHandValueRoyalFlush() {
        Hand hand = new Hand("CT CJ CQ CK CA");
        assertEquals(HandVal.ROYAL_FLUSH, hand.getHandValue());
    }
    @Test
    void getHandValueStraightFlush() {
        Hand hand = hand = new Hand("D8 DQ DJ DT D9");
        assertEquals(HandVal.STRAIGHT_FLUSH, hand.getHandValue());

        hand = new Hand("H8 HT HJ H7 H9");
        assertEquals(HandVal.STRAIGHT_FLUSH, hand.getHandValue());
    }
    @Test
    void getHandValuePoker() {
        Hand hand = hand = new Hand("HT SQ ST DT CT");
        assertEquals(HandVal.POKER, hand.getHandValue());

        hand = new Hand("HT SK ST DT CT");
        assertEquals(HandVal.POKER, hand.getHandValue());

        hand = new Hand("H8 SQ S8 D8 C8");
        assertEquals(HandVal.POKER, hand.getHandValue());

        hand = new Hand("H7 SK S7 D7 C7");
        assertEquals(HandVal.POKER, hand.getHandValue());
    }
    @Test
    void getHandValueFullHouse() {
        Hand hand = hand = new Hand("H2 SQ C2 D2 CQ");
        assertEquals(HandVal.FULL_HOUSE, hand.getHandValue());

        hand = new Hand("D2 DJ D2 D2 DJ");
        assertEquals(HandVal.FULL_HOUSE, hand.getHandValue());

        hand = new Hand("H2 SJ C2 D2 CJ");
        assertEquals(HandVal.FULL_HOUSE, hand.getHandValue());

        hand = new Hand("D2 DJ D2 D2 DJ");
        assertEquals(HandVal.FULL_HOUSE, hand.getHandValue());
    }
    @Test
    void getHandValueFlush() {
        Hand hand = hand = new Hand("HK HQ H2 H4 H5");
        assertEquals(HandVal.FLUSH, hand.getHandValue());

        hand = new Hand("HK HQ H2 H4 H5");
        assertEquals(HandVal.FLUSH, hand.getHandValue());

        hand = new Hand("D5 D4 D2 DQ DK");
        assertEquals(HandVal.FLUSH, hand.getHandValue());
    }
    @Test
    void getHandValueStraight() {
        Hand hand = hand = new Hand("H3 S7 H5 D6 H4");
        assertEquals(HandVal.STRAIGHT, hand.getHandValue());

        hand = new Hand("C9 CT SJ D7 H8");
        assertEquals(HandVal.STRAIGHT, hand.getHandValue());

        hand = new Hand("H4 S5 HA D3 H2");
        assertEquals(HandVal.STRAIGHT, hand.getHandValue());
    }
    @Test
    void getHandValueThreeOfAKind() {
        Hand hand = hand = new Hand("H2 SQ S2 D2 CK");
        assertEquals(HandVal.THREE_OF_A_KIND, hand.getHandValue());

        hand = new Hand("H2 S7 S2 D2 C9");
        assertEquals(HandVal.THREE_OF_A_KIND, hand.getHandValue());

        hand = new Hand("H2 S8 S2 D2 C9");
        assertEquals(HandVal.THREE_OF_A_KIND, hand.getHandValue());
    }
    @Test
    void getHandValueTwoPairs() {
        Hand hand = hand = new Hand("H5 SQ C5 DT CT");
        assertEquals(HandVal.TWO_PAIRS, hand.getHandValue());

        hand = new Hand("D5 DK S5 DT DT");
        assertEquals(HandVal.TWO_PAIRS, hand.getHandValue());

        hand = new Hand("H9 SQ C9 DT CT");
        assertEquals(HandVal.TWO_PAIRS, hand.getHandValue());

        hand = new Hand("D8 DK S8 DT DT");
        assertEquals(HandVal.TWO_PAIRS, hand.getHandValue());
    }
    @Test
    void getHandValueOnePair() {
        Hand hand = hand = new Hand("H3 S8 H5 D8 CA");
        assertEquals(HandVal.ONE_PAIR, hand.getHandValue());

        hand = new Hand("S4 DA H3 CA HT");
        assertEquals(HandVal.ONE_PAIR, hand.getHandValue());
    }
    @Test
    void getHandValueHighCard() {
        Hand hand = new Hand("H3 S8 H5 DK CA");
        assertEquals(HandVal.HIGH_CARD, hand.getHandValue());

        hand = new Hand("H3 S8 H5 DK CT");
        assertEquals(HandVal.HIGH_CARD, hand.getHandValue());

        hand = new Hand("H3 S8 H5 DK C2");
        assertEquals(HandVal.HIGH_CARD, hand.getHandValue());
    }
}