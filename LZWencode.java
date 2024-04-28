// 1604564
// William Malone

import java.io.*;
import java.util.*;

public class LZWencode {
    
    //
    // Main method to run LZWencode
    //
    public static void main(String[] args) {
        // Initialize trie
        Trie trie = new Trie();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // Encode input data and print the encoded sequence
            List<String> encodedData = trie.encodeInputData(reader);
            reader.close();

            // Concatenate the elements of encodedData into a single string
            StringBuilder concatenatedString = new StringBuilder();
            for (String encoded : encodedData) {
                concatenatedString.append(encoded);
            }

            // Print the concatenated output string
            System.out.println(concatenatedString.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //
    // Method for Multiway Trie structure for the dictionary
    //
    private static class Trie {
        public static final int SIZE = 16; // Number of possible hex digits (0-F)
        private Node root; // Root node of the trie


        // Node class definition
        private static class Node {
            Node[] children; // Array to store child nodes (0-F)
            boolean isEndOfWord; // Flag to indicate the end of a word
            int code; //Centents at the phrase 

            // Constructor
            public Node() {
                children = new Node[SIZE]; // Initialize array of children
                isEndOfWord = false; // Initially, not the end of any word
                code = -1; // Default code value
            }
        }


        // Constructor
        public Trie() {
            // Initialize the root node
            root = new Node();
            // Initialize the tree
            populateInitialDictionary();
        }


        // Method to populate the trie with the symbols 0 to F
        private void populateInitialDictionary() {
            for (int i = 0; i < SIZE; i++) {
                char symbol = Character.forDigit(i, 16); // Convert integer to hex digit character
                addSymbol(symbol, i);
                root.children[i].isEndOfWord = true;
            }
        }


        // Method to add a symbol to the trie
        public void addSymbol(char symbol, int code) {
            // Convert the hex digit to its corresponding index (0-15)
            int index = Character.digit(symbol, 16);
            // If the index is not within range, return
            if (index < 0 || index >= SIZE) {
                return;
            }
            // Create a new node if the child node doesn't exist
            if (root.children[index] == null) {
                root.children[index] = new Node();
            }
            root.children[index].code = code;
        }

        
        // Method to encode input data and return the encoded output as a List<String>
        public List<String> encodeInputData(BufferedReader reader) throws IOException {
            List<String> encodedOutput = new ArrayList<>(); // Initialize list to store encoded output
            StringBuilder currentSequence = new StringBuilder();
            int phraseNumber = SIZE; // Start phrase number from the next available code (SIZE)
            
            String line;
            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    char symbol = line.charAt(i);
                    currentSequence.append(symbol);

                    // Check if the current sequence exists in the trie
                    boolean sequenceExists = sequenceExists(currentSequence.toString());

                    // If the current sequence exists, continue appending characters
                    if (sequenceExists) {
                        continue;
                    }

                    // If the current sequence doesn't exist, add it to the trie
                    addSequenceToTrie(currentSequence.toString(), phraseNumber);
                    
                    
                    // Add the current sequence to the encoded output
                    encodedOutput.add(Integer.toString(getCodeForSequence(currentSequence.substring(0, currentSequence.length() - 1))));
                    encodedOutput.add(" ");
                    
                    // Start a new sequence with the current character
                    currentSequence.setLength(0);
                    currentSequence.append(symbol);
                    phraseNumber++; // Increment phrase number
                }
            }
            
            // Add the last sequence to the encoded output
            if (currentSequence.length() > 0) {
                encodedOutput.add(Integer.toString(getCodeForSequence(currentSequence.toString())));
            }

        return encodedOutput; // Return the list containing the encoded output
        }


        // Method to get the code for a sequence from the trie
        private int getCodeForSequence(String sequence) {
            Node current = root;
            for (int i = 0; i < sequence.length(); i++) {
                char symbol = sequence.charAt(i);
                // Convert the hex digit to its corresponding index (0-15)
                int index = Character.digit(symbol, 16);
                if (index < 0 || index >= SIZE || current.children[index] == null) {
                    // Sequence not found in the trie
                    throw new IllegalArgumentException("Invalid sequence in trie");
                }
                current = current.children[index];
            }
            // Return the code associated with the sequence
            return current.code;
        }

        // Method to add a sequence to the trie
        private void addSequenceToTrie(String sequence, int phraseNumber) {
            Node current = root;
            for (int i = 0; i < sequence.length(); i++) {
                char symbol = sequence.charAt(i);
                // Convert the hex digit to its corresponding index (0-15)
                int index = Character.digit(symbol, 16);
                // If the index is not within range, return
                if (index < 0 || index >= SIZE) {
                    return;
                }
                // Create a new node if the child node doesn't exist
                if (current.children[index] == null) {
                    current.children[index] = new Node();
                    current.children[index].code = phraseNumber;
                }
                // Move to the next node
                current = current.children[index];
            }
            // Set the end of word flag for the last node of the sequence
            current.isEndOfWord = true;
        }
    

        // Method to check if a sequence exists in the trie
        private boolean sequenceExists(String sequence) {
            Node current = root;
            // Traverse the trie based on the hexadecimal digits of the sequence
            for (int i = 0; i < sequence.length(); i++) {
                char symbol = sequence.charAt(i);
                int index = Character.digit(symbol, 16); // Convert character to its corresponding index (0-15)
                // If the index is not within range or the child node doesn't exist, return false
                if (index < 0 || index >= SIZE || current.children[index] == null) {
                    return false;
                }
                current = current.children[index];
            }
            return true; // Sequence found in the trie
        }
    }
}
