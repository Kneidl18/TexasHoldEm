package main

import (
	"fmt"
	"strings"
)

// Card represents a playing card with a Suit and a Rank
type Card struct {
	Suit Suit
	Rank Rank
}

// NewCard creates a new Card with the given Suit and Rank
func NewCard(suit Suit, rank Rank) Card {
	return Card{
		Suit: suit,
		Rank: rank,
	}
}

// NewCardFromString creates a new Card from a two-character string description
// Examples: "D2" -> Diamond-Two, "HA" -> Heart-Ace, "ST" -> Spade-Ten, "CK" -> Club-King
func NewCardFromString(cardDesc string) (Card, error) {
	cardDesc = strings.ToUpper(cardDesc) // Ensure the input is uppercase
	if len(cardDesc) != 2 {
		return Card{}, fmt.Errorf("invalid card description: %s", cardDesc)
	}

	// Parse the suit
	var suit Suit
	switch cardDesc[0] {
	case 'C':
		suit = Club
	case 'D':
		suit = Diamond
	case 'H':
		suit = Heart
	case 'S':
		suit = Spade
	default:
		return Card{}, fmt.Errorf("invalid suit: %c", cardDesc[0])
	}

	// Parse the rank
	var rank Rank
	switch cardDesc[1] {
	case '2':
		rank = Two
	case '3':
		rank = Three
	case '4':
		rank = Four
	case '5':
		rank = Five
	case '6':
		rank = Six
	case '7':
		rank = Seven
	case '8':
		rank = Eight
	case '9':
		rank = Nine
	case 'T':
		rank = Ten
	case 'J':
		rank = Jack
	case 'Q':
		rank = Queen
	case 'K':
		rank = King
	case 'A':
		rank = Ace
	default:
		return Card{}, fmt.Errorf("invalid rank: %c", cardDesc[1])
	}

	return Card{Suit: suit, Rank: rank}, nil
}

// GetRank returns the rank of the card
func (c Card) GetRank() Rank {
	return c.Rank
}

// GetSuit returns the suit of the card
func (c Card) GetSuit() Suit {
	return c.Suit
}

// String returns a string representation of the card
func (c Card) String() string {
	return fmt.Sprintf("%s-%d", c.Suit, c.Rank)
}

/*
func main() {
	// Example usage
	cardDesc := "HA"
	card, err := NewCardFromString(cardDesc)
	if err != nil {
		fmt.Println("Error:", err)
		return
	}
	fmt.Println("Card:", card)
	fmt.Println("Suit:", card.GetSuit())
	fmt.Println("Rank:", card.GetRank())
}
*/
