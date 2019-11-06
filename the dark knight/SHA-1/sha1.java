import java.util.Scanner;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class sha1 {
    public static void main(String args[]) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter string to be encrypted :");
        String input = scanner.nextLine();
        System.out.println("Input :" + input);
        System.out.println("Output : " + encrypt(input));
        scanner.close();
    }

    private static String encrypt(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] message = md.digest(input.getBytes());
            BigInteger n = new BigInteger(1, message);
            String hash = n.toString(16);
            while (hash.length() < 32) {
                hash = "0" + hash;
            }
            return hash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}