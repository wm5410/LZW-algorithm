//NEW WORKKING


// import java.io.*;
// import java.util.*;

// public class LZWencode {
    
//     public static void main(String[] args) {
//         // Initialize trie and populate with symbols 0 to F
//         Trie trie = new Trie();

//         try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
//             // Encode input data and print the encoded sequence
//             trie.encodeInputData(reader);
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     // Static inner class Trie
//     private static class Trie {
//         public static final int SIZE = 16; // Number of possible hex digits (0-F)
//         private Node root; // Root node of the trie

//         // Node class definition
//         private static class Node {
//             Node[] children; // Array to store child nodes (0-F)
//             boolean isEndOfWord; // Flag to indicate the end of a word

//             public Node() {
//                 children = new Node[SIZE]; // Initialize array of children
//                 isEndOfWord = false; // Initially, not the end of any word
//             }
//         }

//         // Constructor
//         public Trie() {
//             // Initialize the root node
//             root = new Node();
//             populateInitialDictionary();
//         }

//         // Method to populate the trie with the symbols 0 to F
//         private void populateInitialDictionary() {
//             for (int i = 0; i < SIZE; i++) {
//                 char symbol = Character.forDigit(i, 16); // Convert integer to hex digit character
//                 addSymbol(symbol);
//                 System.out.println(symbol + " " + i);
//             }
//         }

//         // Method to add a symbol to the trie
//         public void addSymbol(char symbol) {
//             // Convert the hex digit to its corresponding index (0-15)
//             int index = Character.digit(symbol, 16);
//             // If the index is not within range, return
//             if (index < 0 || index >= SIZE) {
//                 return;
//             }
//             // Create a new node if the child node doesn't exist
//             if (root.children[index] == null) {
//                 root.children[index] = new Node();
//             }
//         }

//         public void encodeInputData(BufferedReader reader) throws IOException {
//             String line;
//             StringBuilder currentSequence = new StringBuilder();
//             while ((line = reader.readLine()) != null) {
//                 for (int i = 0; i < line.length(); i++) {
//                     char symbol = line.charAt(i);
//                     // Append the character to the current sequence
//                     currentSequence.append(symbol);
//                     // Check if the current sequence exists in the trie
//                     boolean sequenceExists = sequenceExists(currentSequence.toString());
//                     // If the current sequence exists, continue appending characters
//                     if (sequenceExists) {
//                         continue;
                        
//                     }
//                     // If the current sequence doesn't exist, print the previous sequence and reset
//                     if (currentSequence.length() > 1) {
//                         System.out.println("no exist");
//                         //System.out.println("Seq: " + currentSequence.substring(0, currentSequence.length() - 1));
//                     }
//                     // Start a new sequence with the current character
//                     currentSequence.setLength(0);
//                     currentSequence.append(symbol);
//                 }
//                 // Print the last sequence in the line if it exists
//                 if (currentSequence.length() > 0) {
//                     System.out.println("Seq: " + currentSequence);
//                     // Reset the current sequence for the next line
//                     currentSequence.setLength(0);
//                 }
//             }
//         }
        


//         // Method to check if a sequence exists in the trie
//         private boolean sequenceExists(String sequence) {
//             System.out.println("Seq: " + sequence);
//             Node current = root;
//             // Traverse the trie based on the hexadecimal digits of the sequence
//             for (int i = 0; i < sequence.length(); i++) {
//                 char symbol = sequence.charAt(i);
//                 int index = Character.digit(symbol, 16); // Convert character to its corresponding index (0-15)
//                 // If the index is not within range or the child node doesn't exist, return false
//                 if (index < 0 || index >= SIZE || current.children[index] == null) {
//                     //System.out.println("Sequence not exists");
//                     return false;
//                 }
//                 current = current.children[index];
//             }
//             //System.out.println("Sequence exists");
//             return true; // Sequence found in the trie
//         }


//     }
// }












//WORKING



// import java.io.*;
// import java.util.*;

// public class LZWencode {
    
//     public static void main(String[] args) {
//         // Initialize trie and populate with symbols 0 to F
//         Trie trie = new Trie();

//         try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
//             // Read the input from standard input (output of ByteToHex program)
//             StringBuilder hexBuilder = new StringBuilder();
//             int byteValue;
//             int position = Trie.SIZE; // Initialize position counter to the size of the initial dictionary
//             while ((byteValue = reader.read()) != -1) {
//                 char c = (char) byteValue;
//                 hexBuilder.append(c);

//                 // Add the new symbol to the trie
//                 trie.addSymbol(c);
//                 position++;
//             }
            
//             // Print the trie contents
//             //trie.printTrieContents(position);

//             // Encode input data
//             trie.encodeInputData(reader, position);
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     // Static inner class Trie
//     private static class Trie {
//         public static final int SIZE = 16; // Number of possible hex digits (0-F)

//         // Node class definition
//         private static class Node {
//             byte symbol; // Byte represented by this node
//             Node nextNode; // Pointer to the next node (same level)

//             public Node(byte symbol) {
//                 this.symbol = symbol;
//                 this.nextNode = null;
//             }
//         }

//         // Trie attributes
//         private Node root; // Root node of the trie

//         // Constructor
//         public Trie() {
//             // Initialize the root node
//             root = new Node((byte) 0);

//             // Populate the root with hex digits 0-F 
//             Node current = root;
//             for (int i = 1; i < SIZE; i++) {
//                 byte hexDigit = (byte) Character.forDigit(i, 16); // Convert integer to hex digit
//                 current.nextNode = new Node(hexDigit); // Create a new node for the hex digit
//                 current = current.nextNode; // Move to the next node
//             }
//         }



//     // Method to encode input data
//     public void encodeInputData(BufferedReader reader, int position) throws IOException {

//         Node current = root.nextNode; 
//             while (current != null) {
//                 System.out.println("Symbol: " + (char) current.symbol + ", Index in Trie: " + findIndex((char) current.symbol)); //+ ", Position: " + position);
//                 current = current.nextNode;
//                 position++;
//             }
//     }

//     private boolean symbolExists(char symbol) {
//         Node current = root.nextNode;
//         while (current != null) {
//             if (current.symbol == symbol) {
//                 return true;
//             }
//             current = current.nextNode;
//         }
//         return false;
//     }


//         // Method to add a new symbol to the trie
//         public void addSymbol(char symbol) {
//             Node current = root;
//             while (current.nextNode != null) {
//                 current = current.nextNode;
//             }
//             current.nextNode = new Node((byte) symbol);
//         }

        
//         // Method to print the trie contents (for debugging purposes)
//         public void printTrieContents(int position) {
//             System.out.println("Trie Contents:");
//             Node current = root.nextNode; // Start from the first node (skip the root)
//             while (current != null) {
//                 System.out.println("Symbol: " + (char) current.symbol + ", Index in Trie: " + findIndex((char) current.symbol)); //+ ", Position: " + position);
//                 current = current.nextNode;
//                 position++;
//             }
//         }

//         // Method to find the index of a symbol in the trie
//         public int findIndex(char symbol) {
//             Node current = root.nextNode; // Start from the first node (skip the root)
//             int index = 0;
//             while (current != null) {
//                 if (current.symbol == symbol) {
//                     return index; // Return the index if symbol is found
//                 }
//                 current = current.nextNode;
//                 index++;
//             }
//             return -1; // Return -1 if symbol is not found
//         }
//     }
// }


