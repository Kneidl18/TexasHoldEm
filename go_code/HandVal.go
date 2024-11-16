package main

type HandVal string

const (
	RoyalFlush    HandVal = "Royal Flush"
	StraightFlush HandVal = "Straight Flush"
	FourOfAKind   HandVal = "Four of a Kind"
	FullHouse     HandVal = "Full House"
	Flush         HandVal = "Flush"
	Straight      HandVal = "Straight"
	ThreeOfAKind  HandVal = "Three of a Kind"
	TwoPairs      HandVal = "Two Pairs"
	Pair          HandVal = "Pair"
	HighCard      HandVal = "High Card"
)

// String provides a string representation for each HandVal
func (hv HandVal) String() string {
	switch hv {
	case RoyalFlush:
		return "ROYAL_FLUSH"
	case StraightFlush:
		return "STRAIGHT_FLUSH"
	case FourOfAKind:
		return "FOUR_OF_A_KIND"
	case FullHouse:
		return "FULL_HOUSE"
	case Flush:
		return "FLUSH"
	case Straight:
		return "STRAIGHT"
	case ThreeOfAKind:
		return "THREE_OF_A_KIND"
	case TwoPairs:
		return "TWO_PAIRS"
	case Pair:
		return "PAIR"
	case HighCard:
		return "HIGH_CARD"
	default:
		return "UNKNOWN_HAND"
	}
}
