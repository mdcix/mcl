import java.util.Scanner;

public class ChineseRemainderTheorem {

    // Function to compute the modular inverse using Extended Euclidean Algorithm
    public static long modularInverse(long a, long m) {
        long[] result = extendedGCD(a, m);
        long gcd = result[0];
        long x = result[1];
        if (gcd != 1) {
            throw new ArithmeticException("No modular inverse exists.");
        }
        return (x % m + m) % m;
    }

    // Extended Euclidean Algorithm to find the GCD and coefficients
    private static long[] extendedGCD(long a, long b) {
        if (b == 0) {
            return new long[]{a, 1, 0};
        }
        long[] result = extendedGCD(b, a % b);
        long gcd = result[0];
        long x1 = result[1];
        long y1 = result[2];
        long x = y1;
        long y = x1 - (a / b) * y1;
        return new long[]{gcd, x, y};
    }

    // Function to solve the system of congruences using the Chinese Remainder Theorem
    public static long chineseRemainder(long[] a, long[] m) {
        long M = 1;
        for (long mod : m) {
            M *= mod;
        }

        long x = 0;
        for (int i = 0; i < a.length; i++) {
            long ai = a[i];
            long mi = m[i];
            long Mi = M / mi;
            long invMi = modularInverse(Mi, mi);
            x = (x + ai * Mi * invMi) % M;
        }
        return (x + M) % M; // Ensure result is non-negative
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of congruences
        System.out.print("Enter number of congruences: ");
        int k = scanner.nextInt();

        long[] a = new long[k];
        long[] m = new long[k];

        // Read congruences
        for (int i = 0; i < k; i++) {
            System.out.print("Enter a" + (i + 1) + ": ");
            a[i] = scanner.nextLong();
            System.out.print("Enter m" + (i + 1) + ": ");
            m[i] = scanner.nextLong();
        }

        // Solve the system of congruences
        try {
            long result = chineseRemainder(a, m);
            System.out.println("The solution is: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
