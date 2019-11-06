import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA {
    public static int bitlength = 512;
    public static Random r = new Random();
    public static BigInteger p = BigInteger.probablePrime(bitlength, r);
    public static BigInteger q = BigInteger.probablePrime(bitlength, r);
    public static BigInteger n = p.multiply(q);
    public static BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
    public static BigInteger d;
    public static BigInteger e = BigInteger.probablePrime(bitlength / 2, r);

    public static void main(String args[]) {
        // While gcd(phi,e) > 1 and e < phi
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e.add(BigInteger.ONE);
        }

        d = e.modInverse(phi);
        System.out.println("Public key : " + e);
        System.out.println("Private key : " + d);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter plaintext :");
        String plaintext = sc.nextLine();
        sc.close();
        System.out.println("\nPlaintext : " + plaintext);
	System.out.println("\nPlaintext in bytes : " + bytestoString(plaintext.getBytes()));
        byte encrypted[] = encrypt(plaintext.getBytes()); 
        System.out.println("\nCiphertext in bytes : " + bytestoString(encrypted));
        byte decrypted[] = decrypt(encrypted);
        System.out.println("\nPlaintext after decrypting : " + new String(decrypted));
    }

    private static String bytestoString(byte[] encrypted) {
        StringBuilder ciphertext = new StringBuilder();
        for (byte b : encrypted) {
            ciphertext.append(Byte.toString(b));
        }
        return ciphertext.toString();
    }

    private static byte[] encrypt(byte[] message) {
        return (new BigInteger(message)).modPow(e, n).toByteArray();
    }

    private static byte[] decrypt(byte[] message) {
        return (new BigInteger(message)).modPow(d, n).toByteArray();
    }

}
