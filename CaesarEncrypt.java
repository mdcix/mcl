import java.util.Scanner;

public class CaesarEncrypt {
    public static String encrypt(String plaintext, int shift) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            char base = Character.isUpperCase(c) ? 'A' : 'a';
            encrypted.append((char) ((c - base + shift) % 26 + base));
        }
        return encrypted.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext to encrypt: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter the shift value (an integer): ");
        int shift = scanner.nextInt();

        String encrypted = encrypt(plaintext, shift);
        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted: " + encrypted);

        scanner.close();
    }
}