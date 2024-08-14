import java.util.Scanner;

public class CaesarBruteForce {
    public static String decrypt(String ciphertext, int shift) {
        StringBuilder plaintext = new StringBuilder();
        for (char c : ciphertext.toCharArray()) {
            char base = Character.isUpperCase(c) ? 'A' : 'a';
            plaintext.append((char) ((c - base - shift + 26) % 26 + base));
        }
        return plaintext.toString();
    }

    public static void bruteForceAttack(String ciphertext) {
        System.out.println("Brute Force Results:");
        for (int shift = 0; shift < 26; shift++) {
            String decrypted = decrypt(ciphertext, shift);
            System.out.printf("Shift %2d: %s%n", shift, decrypted);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Caesar cipher encrypted text: ");
        String ciphertext = scanner.nextLine();
        scanner.close();

        bruteForceAttack(ciphertext);
    }
}