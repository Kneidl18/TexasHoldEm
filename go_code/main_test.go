package main

import (
	"testing"
)

func BenchmarkBenchmarkTestGetHandValueRoyalFlush(b *testing.B) {
	hand, _ := NewHandFromString("CT CJ CQ CK CA")
	if hand.HandValue != RoyalFlush {
		b.Errorf("Expected RoyalFlush, got %v", hand.HandValue)
	}
}

func BenchmarkTestGetHandValueStraightFlush(b *testing.B) {
	hand, _ := NewHandFromString("D8 DQ DJ DT D9")
	if hand.HandValue != StraightFlush {
		b.Errorf("Expected StraightFlush, got %v", hand.HandValue)
	}

	hand, _ = NewHandFromString("H8 HT HJ H7 H9")
	if hand.HandValue != StraightFlush {
		b.Errorf("Expected StraightFlush, got %v", hand.HandValue)
	}
}

func BenchmarkTestGetHandValuePoker(b *testing.B) {
	tests := []struct {
		cards   string
		wantVal HandVal
	}{
		{"HT SQ ST DT CT", FourOfAKind},
		{"HT SK ST DT CT", FourOfAKind},
		{"H8 SQ S8 D8 C8", FourOfAKind},
		{"H7 SK S7 D7 C7", FourOfAKind},
	}

	for _, tt := range tests {
		hand, _ := NewHandFromString(tt.cards)
		if hand.HandValue != tt.wantVal {
			b.Errorf("For hand %v, expected %v, got %v", tt.cards, tt.wantVal, hand.HandValue)
		}
	}
}

func BenchmarkTestGetHandValueFullHouse(b *testing.B) {
	tests := []struct {
		cards   string
		wantVal HandVal
	}{
		{"H2 SQ C2 D2 CQ", FullHouse},
		{"D2 DJ D2 D2 DJ", FullHouse},
		{"H2 SJ C2 D2 CJ", FullHouse},
	}

	for _, tt := range tests {
		hand, _ := NewHandFromString(tt.cards)
		if hand.HandValue != tt.wantVal {
			b.Errorf("For hand %v, expected %v, got %v", tt.cards, tt.wantVal, hand.HandValue)
		}
	}
}

func BenchmarkTestGetHandValueFlush(b *testing.B) {
	tests := []struct {
		cards   string
		wantVal HandVal
	}{
		{"HK HQ H2 H4 H5", Flush},
		{"D5 D4 D2 DQ DK", Flush},
	}

	for _, tt := range tests {
		hand, _ := NewHandFromString(tt.cards)
		if hand.HandValue != tt.wantVal {
			b.Errorf("For hand %v, expected %v, got %v", tt.cards, tt.wantVal, hand.HandValue)
		}
	}
}

func BenchmarkTestGetHandValueStraight(b *testing.B) {
	tests := []struct {
		cards   string
		wantVal HandVal
	}{
		{"H3 S7 H5 D6 H4", Straight},
		{"C9 CT SJ D7 H8", Straight},
		{"H4 S5 H6 D3 H2", Straight},
	}

	for _, tt := range tests {
		hand, _ := NewHandFromString(tt.cards)
		if hand.HandValue != tt.wantVal {
			b.Errorf("For hand %v, expected %v, got %v", tt.cards, tt.wantVal, hand.HandValue)
		}
	}
}

func BenchmarkTestGetHandValueThreeOfAKind(b *testing.B) {
	tests := []struct {
		cards   string
		wantVal HandVal
	}{
		{"H2 SQ S2 D2 CK", ThreeOfAKind},
		{"H2 S7 S2 D2 C9", ThreeOfAKind},
		{"H2 S8 S2 D2 C9", ThreeOfAKind},
	}

	for _, tt := range tests {
		hand, _ := NewHandFromString(tt.cards)
		if hand.HandValue != tt.wantVal {
			b.Errorf("For hand %v, expected %v, got %v", tt.cards, tt.wantVal, hand.HandValue)
		}
	}
}

func BenchmarkTestGetHandValueTwoPairs(b *testing.B) {
	tests := []struct {
		cards   string
		wantVal HandVal
	}{
		{"H5 SQ C5 DT CT", TwoPairs},
		{"D5 DK S5 DT DT", TwoPairs},
		{"H9 SQ C9 DT CT", TwoPairs},
		{"D8 DK S8 DT DT", TwoPairs},
	}

	for _, tt := range tests {
		hand, _ := NewHandFromString(tt.cards)
		if hand.HandValue != tt.wantVal {
			b.Errorf("For hand %v, expected %v, got %v", tt.cards, tt.wantVal, hand.HandValue)
		}
	}
}

func BenchmarkTestGetHandValueOnePair(b *testing.B) {
	tests := []struct {
		cards   string
		wantVal HandVal
	}{
		{"H3 S8 H5 D8 CA", Pair},
		{"S4 DA H3 CA HT", Pair},
	}

	for _, tt := range tests {
		hand, _ := NewHandFromString(tt.cards)
		if hand.HandValue != tt.wantVal {
			b.Errorf("For hand %v, expected %v, got %v", tt.cards, tt.wantVal, hand.HandValue)
		}
	}
}

func BenchmarkTestGetHandValueHighCard(b *testing.B) {
	tests := []struct {
		cards   string
		wantVal HandVal
	}{
		{"H3 S8 H5 DK CA", HighCard},
		{"H3 S8 H5 DK CT", HighCard},
		{"H3 S8 H5 DK C2", HighCard},
	}

	for _, tt := range tests {
		hand, _ := NewHandFromString(tt.cards)
		if hand.HandValue != tt.wantVal {
			b.Errorf("For hand %v, expected %v, got %v", tt.cards, tt.wantVal, hand.HandValue)
		}
	}
}
