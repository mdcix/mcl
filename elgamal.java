import java.math.BigInteger;
import java.util.Scanner;

public class elgamal {

    private BigInteger p; // large prime number
    private BigInteger e1; // first public value
    private BigInteger e2; // second public value
    private BigInteger d;  // private key

    // Constructor to initialize fixed values
    public elgamal() {
        p = BigInteger.valueOf(11); // set prime p
        e1 = BigInteger.valueOf(2);  // set e1
        e2 = BigInteger.valueOf(8);  // set e2
        d = BigInteger.valueOf(3);   // set private key d
    }

    // Encrypt message
    public BigInteger[] encrypt(BigInteger pt, int r) {
        BigInteger rBigInt = BigInteger.valueOf(r);
        BigInteger c1 = e1.modPow(rBigInt, p); // c1 = e1^r mod p
        BigInteger c2 = e2.modPow(rBigInt, p).multiply(pt).mod(p); // c2 = (e2^r * pt) mod p
        return new BigInteger[] {c1, c2};  // return ciphertext as [c1, c2]
    }

    // Decrypt message using derived d
    public BigInteger decrypt(BigInteger[] ciphertext) {
        BigInteger c1 = ciphertext[0];
        BigInteger c2 = ciphertext[1];
        BigInteger c1d = c1.modPow(d, p); // c1^d mod p
        BigInteger c1dInverse = c1d.modInverse(p); // (c1^d)^-1 mod p
        return c2.multiply(c1dInverse).mod(p); // pt = (c2 * (c1^d)^-1) mod p
    }

    public static void main(String[] args) {
        // Create elgamal instance
        elgamal elgamal = new elgamal();

        // Get user input for plaintext
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a plaintext value (0 < pt < 11): ");
        BigInteger message = scanner.nextBigInteger();

        // Ensure the plaintext is valid
        if (message.compareTo(BigInteger.ZERO) <= 0 || message.compareTo(BigInteger.valueOf(11)) >= 0) {
            System.out.println("Plaintext must be in the range (0, 11).");
            return;
        }

        // Set random r (for demonstration, let's keep it constant)
        int r = 4; // This can be modified for different results

        System.out.println("Original Message: " + message);

        // Encrypt the message
        BigInteger[] ciphertext = elgamal.encrypt(message, r);
        System.out.println("Encrypted: c1 = " + ciphertext[0] + ", c2 = " + ciphertext[1]);

        // Decrypt the message
        BigInteger decryptedMessage = elgamal.decrypt(ciphertext);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}
