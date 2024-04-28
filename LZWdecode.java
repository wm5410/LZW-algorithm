// 1604564
// William Malone

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//
// Main method to run LZWdecode
//
public class LZWdecode {
    public static void main(String[] args) throws IOException {
        Dictionary dictionary  = new Dictionary();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String output = dictionary.decode(reader);
            System.out.println(output);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    // Method to build up dictionary
    //
    private static class Dictionary {
        public static final int SIZE = 16; // Max Number of possible hex digits (0-F)
        ArrayList<String> dictionary;

        // Constructor
        public Dictionary() {
            dictionary = new ArrayList<String>();
            populateInitialDictionary();
        }

        // Method to populate the trie with the symbols 0 to F
        private void populateInitialDictionary() {
            for (int index = 0; index < SIZE; index++) {
                String code = Integer.toHexString(index);
                dictionary.add(code); 
            }
        }


        // Method to decode the input string
        public String decode(BufferedReader reader) throws IOException  {
            int phraseNumber = SIZE;
            StringBuilder currentSequence = new StringBuilder();
            StringBuilder output = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                // Split the input string by spaces
                String[] encodedOutput = line.split("\\s+");

                // Iterate over each symbol in the input and process it 
                for (String phase : encodedOutput) {
                    int index =  Integer.parseInt(phase);;
                    if (index >= 0 && index < dictionary.size()) {
                        String item = dictionary.get(index);
                            
                        for (int i = 0; i < item.length(); i++) {
                            char character = item.charAt(i);
                            currentSequence.append(character);
                            boolean exists = dictionary.contains(currentSequence.toString());
                            // After the sequence is built it shouldnt already exist
                            // Add it to the dictionary
                            if (!exists) {
                                dictionary.add(currentSequence.toString());
                                currentSequence.setLength(0);
                                currentSequence.append(item);
                                phraseNumber++;
                                break;
                            }
                        }
                        // After the current symbol has been processed and added to the dictionary
                        // Check the current symbol and decode it 
                        // Add this to a StringBuilder
                        String code = dictionary.get(index);
                        output.append(code);

                    }
                }
            }
            // Return the decoded output 
            return output.toString();
        }
    }
}
