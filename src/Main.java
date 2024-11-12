public class Main {
    public static void main(String[] args) {
        testHand("CT CJ CQ CK CA", HandVal.ROYAL_FLUSH);

        testHand("D8 DQ DJ DT D9", HandVal.STRAIGHT_FLUSH);
        testHand("H8 HT HJ H7 H9", HandVal.STRAIGHT_FLUSH);

        testHand("HT SQ ST DT CT", HandVal.POKER);
        testHand("HT SK ST DT CT", HandVal.POKER);
        testHand("H8 SQ S8 D8 C8", HandVal.POKER);
        testHand("H7 SK S7 D7 C7", HandVal.POKER);

        testHand("H2 SQ C2 D2 CQ", HandVal.FULL_HOUSE);
        testHand("D2 DJ D2 D2 DJ", HandVal.FULL_HOUSE);
        testHand("H2 SJ C2 D2 CJ", HandVal.FULL_HOUSE);
        testHand("D2 DJ D2 D2 DJ", HandVal.FULL_HOUSE);

        testHand("HK HQ H2 H4 H5", HandVal.FLUSH);
        testHand("HK HQ H2 H4 H5", HandVal.FLUSH);
        testHand("D5 D4 D2 DQ DK", HandVal.FLUSH);

        testHand("H3 S7 H5 D6 H4", HandVal.STRAIGHT);
        testHand("C9 CT SJ D7 H8", HandVal.STRAIGHT);
        testHand("H4 S5 HA D3 H2", HandVal.STRAIGHT);

        testHand("H2 SQ S2 D2 CK", HandVal.THREE_OF_A_KIND);
        testHand("H2 S7 S2 D2 C9", HandVal.THREE_OF_A_KIND);
        testHand("H2 S8 S2 D2 C9", HandVal.THREE_OF_A_KIND);

        testHand("H5 SQ C5 DT CT", HandVal.TWO_PAIRS);
        testHand("D5 DK S5 DT DT", HandVal.TWO_PAIRS);
        testHand("H9 SQ C9 DT CT", HandVal.TWO_PAIRS);
        testHand("D8 DK S8 DT DT", HandVal.TWO_PAIRS);

        testHand("H3 S8 H5 D8 CA", HandVal.ONE_PAIR);
        testHand("S4 DA H3 CA HT", HandVal.ONE_PAIR);

        testHand("H3 S8 H5 DK CA", HandVal.HIGH_CARD);
        testHand("H3 S8 H5 DK CT", HandVal.HIGH_CARD);
        testHand("H3 S8 H5 DK C2", HandVal.HIGH_CARD);
    }
    private static void testHand(String handDesc, HandVal expectedHandVal) {
        Hand hand= new Hand(handDesc);
        System.out.println(hand);
        System.out.print("  =>   " + hand.getHandValue());
        if (hand.getHandValue() == expectedHandVal) {
            System.out.println("  OK");
        } else {
            System.out.println("  NOT OK");
        }
        System.out.println("___________________________");
    }
}


/*
        Hand hand= new Hand("S6    H2 D3  C4     S5");
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

        hand= new Hand("H6 H2 H3  H4 H5");
        System.out.println(hand);
        System.out.println("===============>   " + hand.getHandValue());

        Card card1 = new Card(Suit.SPADE, Rank.ACE);
        Card card2 = new Card(Suit.HEART, Rank.TWO);
        Card card3 = new Card(Suit.DIAMOND, Rank.THREE);
        Card card4 = new Card(Suit.CLUB, Rank.FOUR);
        Card card5 = new Card(Suit.SPADE, Rank.FIVE);

        hand = new Hand(new Card[] {card1, card2, card3, card4, card5});
        System.out.println(hand);
        System.out.println("===============>   " + hand.getHandValue());
 */
