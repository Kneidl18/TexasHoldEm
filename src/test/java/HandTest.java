package test.java;

import main.java.Hand;
import main.java.HandVal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Locale;
import java.util.stream.Stream;

import static java.lang.System.exit;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandTest {
    private static final String fileName = "src/Hands to be tested.txt";

    private static Stream<TestCase> provideTestCases() {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
            exit(1);
        }

        String[] hands = content.split("//");
        Hashtable<String, ArrayList<Hand>> dictHands = new Hashtable<>();

        for (String hand : hands) {
            String[] splitHand = hand.split("\r");

            if (hand == splitHand[0]) continue;

            String handName = splitHand[0].trim().toUpperCase(Locale.ROOT).replace(" ", "_");
            Hand h;
            for (int i = 1; i < splitHand.length; i++) {
                splitHand[i] = splitHand[i].trim().replace("\"", "");
                if (splitHand[i].split(" ").length != 5) { continue; }

                h = new Hand(splitHand[i]);
                if (dictHands.containsKey(handName)) {
                    dictHands.get(handName).add(h);
                } else {
                    ArrayList<Hand> handsList = new ArrayList<>();
                    handsList.add(h);
                    dictHands.put(handName, handsList);
                }
            }
        }

        ArrayList<TestCase> testCases = new ArrayList<>();
        for (String key : dictHands.keySet()) {
            System.out.println("main.java.Hand: " + key);
            for (Hand h : dictHands.get(key)) {
                testCases.add(new TestCase(key, h, h.getHandValue()));
            }
        }

        return testCases.stream();
    }

    // Define parameterized test
    @ParameterizedTest
    @MethodSource("provideTestCases")
    @DisplayName("Test main.java.Hand Evaluation")
    void testHandEvaluation(TestCase testCase) {
        assertEquals(testCase.expectedHandVal, testCase.hand.getHandValue(),
                "Failed for: " + testCase.description);
    }

    // Inner class to define a test case structure
    static class TestCase {
        String description;
        Hand hand;
        HandVal expectedHandVal;

        TestCase(String description, Hand hand, HandVal expectedHandVal) {
            this.description = description;
            this.hand = hand;
            this.expectedHandVal = expectedHandVal;
        }
    }
}
