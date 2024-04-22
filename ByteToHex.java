import java.io.IOException;

import java.io.*;

public class ByteToHex {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java ByteToHex <filename>");
            System.exit(1);
        }

        String filename = args[0];
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(filename))) {
            int byteValue;
            while ((byteValue = reader.read()) != -1) {
                // Convert byte to hexadecimal string
                String hex = String.format("%02x", byteValue); 
                System.out.write(hex.getBytes());
            }
            System.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
