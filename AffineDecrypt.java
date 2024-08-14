import java.util.Scanner;

public class AffineDecrypt {

    public static String decrypt(String ciphertext, int a, int b) {
        StringBuilder plaintext = new StringBuilder();
        int aInverse = findModularMultiplicativeInverse(a, 26);

        for (char ch : ciphertext.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                int y = ch - base;
                int decryptedY = (aInverse * (y - b + 26)) % 26;
                char decryptedChar = (char) (decryptedY + base);
                plaintext.append(decryptedChar);
            } else {
                plaintext.append(ch);
            }
        }

        return plaintext.toString();
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

        System.out.print("Enter the ciphertext: ");
        String ciphertext = scanner.nextLine();

        System.out.print("Enter the value of 'a' (must be coprime with 26): ");
        int a = scanner.nextInt();

        if (findModularMultiplicativeInverse(a, 26) == -1) {
            System.out.println("Error: 'a' is not coprime with 26. Decryption is not possible.");
            scanner.close();
            return;
        }

        System.out.print("Enter the value of 'b': ");
        int b = scanner.nextInt();

        String plaintext = decrypt(ciphertext, a, b);
        System.out.println("Decrypted text: " + plaintext);

        scanner.close();
    }
}