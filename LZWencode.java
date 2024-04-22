import java.io.*;
import java.util.*;

public class LZWencode {
    
    public static void main(String[] args) {
        // Initialize trie and populate with symbols 0 to F
        Trie trie = new Trie();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // Encode input data and print the encoded sequence
            trie.encodeInputData(reader);
            System.out.println("--------------------------------");
            trie.printTrieContents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Static inner class Trie
    private static class Trie {
        public static final int SIZE = 16; // Number of possible hex digits (0-F)
        private Node root; // Root node of the trie

        // Node class definition
        private static class Node {
            Node[] children; // Array to store child nodes (0-F)
            boolean isEndOfWord; // Flag to indicate the end of a word

            public Node() {
                children = new Node[SIZE]; // Initialize array of children
                isEndOfWord = false; // Initially, not the end of any word
            }
        }

        // Constructor
        public Trie() {
            // Initialize the root node
            root = new Node();
            populateInitialDictionary();
        }

        // Method to populate the trie with the symbols 0 to F
        private void populateInitialDictionary() {
            for (int i = 0; i < SIZE; i++) {
                char symbol = Character.forDigit(i, 16); // Convert integer to hex digit character
                addSymbol(symbol);
                System.out.println(symbol + " " + i);
            }
            System.out.println("--------------------------------------------------------");
        }

        // Method to add a symbol to the trie
        public void addSymbol(char symbol) {
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
        }

            // Method to encode input data
    public void encodeInputData(BufferedReader reader) throws IOException {
        String line;
        StringBuilder currentSequence = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            for (int i = 0; i < line.length(); i++) {
                char symbol = line.charAt(i);
                // Append the character to the current sequence
                currentSequence.append(symbol);
                // Check if the current sequence exists in the trie
                boolean sequenceExists = sequenceExists(currentSequence.toString());
                // If the current sequence exists, continue appending characters
                if (sequenceExists) {
                    continue;
                }
                // If the current sequence doesn't exist, add it to the trie
                addSequenceToTrie(currentSequence.toString());
                // Print the previous sequence and reset
                if (currentSequence.length() > 1) {
                    System.out.println("Sequence not exists: " + currentSequence);
                }
                // Start a new sequence with the current character
                currentSequence.setLength(0);
                currentSequence.append(symbol);
            }
            // Print the last sequence in the line if it exists
            if (currentSequence.length() > 0) {
                System.out.println("Sequence exists: " + currentSequence);
                // Reset the current sequence for the next line
                currentSequence.setLength(0);
            }
        }
    }

// Method to add a new sequence to the trie
private void addSequenceToTrie(String sequence) {
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
        }
        // Move to the next node
        current = current.children[index];
    }
}




        // Method to check if a sequence exists in the trie
        private boolean sequenceExists(String sequence) {
            System.out.println("Seq: " + sequence);
            Node current = root;
            // Traverse the trie based on the hexadecimal digits of the sequence
            for (int i = 0; i < sequence.length(); i++) {
                char symbol = sequence.charAt(i);
                int index = Character.digit(symbol, 16); // Convert character to its corresponding index (0-15)
                // If the index is not within range or the child node doesn't exist, return false
                if (index < 0 || index >= SIZE || current.children[index] == null) {
                    //System.out.println("Sequence not exists");
                    return false;
                }
                current = current.children[index];
            }
            //System.out.println("Sequence exists");
            return true; // Sequence found in the trie
        }

        // Method to print the contents of the trie
        public void printTrieContents() {
            System.out.println("Trie Contents:");
            printNodeContents(root, new StringBuilder(), 0);
        }

        // Helper method to recursively print the contents of each node in the trie
        private void printNodeContents(Node node, StringBuilder sequence, int level) {
            // Indentation based on the depth of the node in the trie
            for (int i = 0; i < level; i++) {
                System.out.print("  ");
            }

            // Print the contents of the current node
            System.out.print("Node " + sequence.toString() + ": ");
            boolean hasChildren = false;
            for (int i = 0; i < SIZE; i++) {
                if (node.children[i] != null) {
                    char symbol = Character.forDigit(i, 16);
                    System.out.print(symbol + " ");
                    hasChildren = true;
                }
            }
            if (!hasChildren) {
                System.out.print("(Empty)");
            }
            System.out.println();

            // Recursively print the contents of child nodes
            for (int i = 0; i < SIZE; i++) {
                if (node.children[i] != null) {
                    char symbol = Character.forDigit(i, 16);
                    sequence.append(symbol);
                    printNodeContents(node.children[i], sequence, level + 1);
                    sequence.setLength(sequence.length() - 1); // Remove the last character
                }
            }
        }


    }
}
