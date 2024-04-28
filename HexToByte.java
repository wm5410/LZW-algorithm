// 1604564
// William Malone

import java.io.IOException;

//
// Main method to convert Hex to Bytes
// It will convert 2 hex characters to bytes
// It will then print it to console as ascii characters
//
public class HexToByte {
    public static void main(String[] args) {
        try {
            int data1, data2;
            while ((data1 = System.in.read()) != -1 && (data2 = System.in.read()) != -1) {
                // Convert ASCII characters to their hexadecimal representation
                String hex = "" + (char) data1 + (char) data2;
                // Parse the hexadecimal string as an integer
                int byteValue = Integer.parseInt(hex.trim(), 16); // Trim any leading/trailing whitespace
                // Write the byte value to standard output
                System.out.write(byteValue);
            }
            System.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
