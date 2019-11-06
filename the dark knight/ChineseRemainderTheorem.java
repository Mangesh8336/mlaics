import java.math.BigInteger;
import java.util.Scanner;

public class ChineseRemainderTheorem {

	public static void main(String[] args) {
		int i, inputcount, X = 0, M = 1;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of equations :");
		inputcount = sc.nextInt();

		int[] a = new int[10];
		int[] m = new int[10];
		int[] y = new int[10];

		for (i = 0; i < inputcount; i++) {
			System.out.println("\nFor equation " + (i + 1) + ", a mod m :");
			System.out.println("Enter value of a :");
			a[i] = sc.nextInt();
			System.out.println("Enter value of m :");
			m[i] = sc.nextInt();
		}

		for (i = 0; i < inputcount; i++) {
			M *= m[i];
		}

		System.out.println("Value of M = " + M);

		for (i = 0; i < inputcount; i++) {
			y[i] = BigInteger.valueOf(M / m[i]).modInverse(BigInteger.valueOf(m[i])).intValue();
		}

		for (i = 0; i < inputcount; i++) {
			X += (a[i] * (M / m[i]) * y[i]);
		}

		System.out.println("Value of X = " + X % M);
	}

}
