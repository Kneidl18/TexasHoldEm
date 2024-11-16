package main

import (
	_ "bufio"
	"fmt"
	"os"
	"strings"
)

const fileName = "Hands_to_be_tested_without_duplicates.txt"

// testHand validates that the hand's computed value matches the expected value
func testHand(expected string, hand *Hand) {
	if hand.HandValue.String() != expected {
		panic(fmt.Sprintf("Test failed! Expected: %s, Got: %s", expected, hand.HandValue.String()))
	}
}

// main is the entry point of the program
func main() {
	// Read the file content
	content, err := os.ReadFile(strings.ReplaceAll(fileName, "_", " "))
	if err != nil {
		fmt.Println("Error reading file:", err)
		os.Exit(1)
	}

	// Split the file content into blocks separated by "//"
	hands := strings.Split(string(content), "//")
	dictHands := make(map[string][]*Hand)

	for _, handBlock := range hands {
		lines := strings.Split(handBlock, "\n")
		if len(lines) == 0 {
			continue
		}

		// The first line of the block is the hand's expected name
		handName := strings.TrimSpace(strings.ToUpper(strings.ReplaceAll(lines[0], " ", "_")))

		for i := 1; i < len(lines); i++ {
			handLine := strings.TrimSpace(strings.ReplaceAll(lines[i], "\"", ""))
			if len(strings.Fields(handLine)) != 5 { // Ensure it's a valid hand
				continue
			}

			// Create a Hand object from the line
			hand, err := NewHandFromString(handLine)
			if err != nil {
				fmt.Println("Error parsing hand:", err)
				continue
			}

			// Add the hand to the map under the appropriate category
			dictHands[handName] = append(dictHands[handName], hand)
		}
	}

	// Test and print results
	for handName, hands := range dictHands {
		fmt.Printf("Hand: %s\n", handName)
		for _, hand := range hands {
			testHand(handName, hand)
		}
	}

	fmt.Println("All tests passed!")
}
