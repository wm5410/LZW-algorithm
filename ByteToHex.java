import java.io.IOException;

public class ByteToHex {
    public static void main(String[] args) {
        try {
            int data;
            while ((data = System.in.read()) != -1) {
                // Convert byte to uppercase hexadecimal string
                String hex = String.format("%02X", data); 
                System.out.write(hex.getBytes());
            }
            System.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
