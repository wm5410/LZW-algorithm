import java.io.IOException;

public class HexToByte {
    public static void main(String[] args) {
        try {
            int data;
            while ((data = System.in.read()) != -1) {
                char c1 = (char) data;
                char c2 = (char) System.in.read();
                String hex = "" + c1 + c2;
                int byteValue = Integer.parseInt(hex, 16);
                System.out.write(byteValue);
            }
            System.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
