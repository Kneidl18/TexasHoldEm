package main

import (
	"fmt"
	"sort"
	"strings"
)

// Hand represents a poker hand
type Hand struct {
	Cards     []Card
	HandValue HandVal
}

// NewHand creates a Hand from a slice of Cards
func NewHand(cards []Card) (*Hand, error) {
	if len(cards) != 5 {
		return nil, fmt.Errorf("invalid number of cards: %d", len(cards))
	}
	hand := &Hand{Cards: cards}
	hand.HandValue = hand.calculateHandValue()
	return hand, nil
}

// NewHandFromString creates a Hand from a string description of cards
// e.g., "D2 HA ST CK S2"
func NewHandFromString(handDesc string) (*Hand, error) {
	cardDescs := strings.Fields(handDesc)
	if len(cardDescs) != 5 {
		return nil, fmt.Errorf("invalid hand description: %s", handDesc)
	}

	cards := make([]Card, 5)
	for i, cardDesc := range cardDescs {
		card, err := parseCard(cardDesc)
		if err != nil {
			return nil, err
		}
		cards[i] = card
	}
	return NewHand(cards)
}

// parseCard parses a single card description into a Card
func parseCard(cardDesc string) (Card, error) {
	if len(cardDesc) < 2 {
		return Card{}, fmt.Errorf("invalid card description: %s", cardDesc)
	}
	suit := Suit(cardDesc[0:1])
	rank, err := parseRank(cardDesc[1:])
	if err != nil {
		return Card{}, err
	}
	return Card{Suit: suit, Rank: rank}, nil
}

// parseRank converts a string to a Rank
func parseRank(rankDesc string) (Rank, error) {
	switch rankDesc {
	case "2":
		return Two, nil
	case "3":
		return Three, nil
	case "4":
		return Four, nil
	case "5":
		return Five, nil
	case "6":
		return Six, nil
	case "7":
		return Seven, nil
	case "8":
		return Eight, nil
	case "9":
		return Nine, nil
	case "T":
		return Ten, nil
	case "J":
		return Jack, nil
	case "Q":
		return Queen, nil
	case "K":
		return King, nil
	case "A":
		return Ace, nil
	default:
		return 0, fmt.Errorf("invalid rank: %s", rankDesc)
	}
}

// calculateHandValue calculates the hand value of the Hand
func (h *Hand) calculateHandValue() HandVal {
	// Sort cards by rank for easier analysis
	sort.Slice(h.Cards, func(i, j int) bool {
		return h.Cards[i].Rank < h.Cards[j].Rank
	})

	isFlush := true
	isStraight := true
	rankCounts := make(map[Rank]int)

	// Check for flush
	firstSuit := h.Cards[0].Suit
	for _, card := range h.Cards {
		if card.Suit != firstSuit {
			isFlush = false
			break
		}
	}

	// Count ranks and check for straight
	for i, card := range h.Cards {
		rankCounts[card.Rank]++
		if i > 0 && h.Cards[i].Rank-h.Cards[i-1].Rank != 1 {
			// Special case for Ace-low straight (A-2-3-4-5)
			if !(i == 4 && h.Cards[0].Rank == Ace && h.Cards[4].Rank == Five) {
				isStraight = false
			}
		}
	}

	// Determine hand value
	if isFlush && isStraight && h.Cards[0].Rank == Ten && h.Cards[4].Rank == Ace {
		return RoyalFlush
	}
	if isFlush && isStraight {
		return StraightFlush
	}
	for _, count := range rankCounts {
		if count == 4 {
			return FourOfAKind
		}
	}
	if contains(rankCounts, 3) && contains(rankCounts, 2) {
		return FullHouse
	}
	if isFlush {
		return Flush
	}
	if isStraight {
		return Straight
	}
	if contains(rankCounts, 3) {
		return ThreeOfAKind
	}
	pairs := 0
	for _, count := range rankCounts {
		if count == 2 {
			pairs++
		}
	}
	if pairs == 2 {
		return TwoPairs
	}
	if pairs == 1 {
		return Pair
	}
	return HighCard
}

// Helper to check if a map contains a value
func contains(m map[Rank]int, val int) bool {
	for _, v := range m {
		if v == val {
			return true
		}
	}
	return false
}

// String representation of the Hand
func (h *Hand) String() string {
	var sb strings.Builder
	for _, card := range h.Cards {
		sb.WriteString(fmt.Sprintf("%s\n", card))
	}
	return strings.TrimSpace(sb.String())
}

/*
func main() {
	// Example usage
	handDesc := "D2 HA ST CK S2"
	hand, err := NewHandFromString(handDesc)
	if err != nil {
		fmt.Println("Error:", err)
		return
	}
	fmt.Println("Hand:", hand)
	fmt.Println("Hand Value:", hand.HandValue)
}
*/
