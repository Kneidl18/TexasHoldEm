package main.java;

// main.java.HandVal corresponds to Texas Hold'em poker hand values
public enum HandVal {
    HIGH_CARD(0),
    PAIR(1),
    TWO_PAIRS(2),
    THREE_OF_A_KIND(3),
    STRAIGHT(4),
    FLUSH(5),
    FULL_HOUSE(6),
    POKER(7),
    FOUR_OF_A_KIND(8),
    STRAIGHT_FLUSH(9),
    ROYAL_FLUSH(10),
    NOT_VALID(11);

    private final int value;

    HandVal(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
