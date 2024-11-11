package main.java;

import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import static java.lang.System.exit;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {
    private static final String fileName = "src/Hands to be tested.txt";

    private static void testHand(String name, Hand hand) {
        assertEquals(name, hand.getHandValue().toString());
    }

    public static void main(String[] args) {
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

        for (String key : dictHands.keySet()) {
            System.out.println("main.java.Hand: " + key);
            for (Hand h : dictHands.get(key)) {
                testHand(key, h);
            }
        }
    }
}