import java.util.Scanner;

public class AffineCipher {

    public static String encrypt(String plaintext, int a, int b) {
        StringBuilder ciphertext = new StringBuilder();

        for (char ch : plaintext.toCharArray()) {
            char base = Character.isUpperCase(ch) ? 'A' : 'a';
            int x = ch - base;
            int encryptedX = (a * x + b) % 26;
            char encryptedChar = (char) (encryptedX + base);
            ciphertext.append(encryptedChar);
        }
        return ciphertext.toString();
    }

    private static int findModularMultiplicativeInverse(int a, int m) {
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1; // If no multiplicative inverse exists
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter the value of 'a' (must be coprime with 26): ");
        int a = scanner.nextInt();

        if (findModularMultiplicativeInverse(a, 26) == -1) {
            System.out.println("Error: 'a' is not coprime with 26. Encryption is not possible.");
            scanner.close();
            return;
        }

        System.out.print("Enter the value of 'b': ");
        int b = scanner.nextInt();

        String ciphertext = encrypt(plaintext, a, b);
        System.out.println("Encrypted text: " + ciphertext);

        scanner.close();
    }
}