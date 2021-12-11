import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * decryption
 */
public class DecryptionDriver {

    public static void main(String[] args) throws IOException {
        //Scanners
        File input = new File("\\Encrypt.rsa");
        Scanner fScanner = new Scanner(input);
        Scanner fScanner2 = new Scanner(input);
        Scanner kbScanner = new Scanner(System.in);
        FileOutputStream outputFile = new FileOutputStream("\\Decrypt.dec");

        RSA rsa = new RSA();

        //Variables
        String decryptedValues = "";
        String decryptedMessage = "";
        String temp;
        long blockSize;
        long passedValue, d, n, max_value = 0;

        //User input
        System.out.print("Enter the value of d: ");
        d = kbScanner.nextLong();
        System.out.print("Enter the value of n: ");
        n = kbScanner.nextLong();
        kbScanner.close();

        String encryptedMessage = fScanner.nextLine(); 
        decryptedMessage = rsa.decrypt(encryptedMessage, n, d);

        //Send decoded message to decrypt.rsa
        for(int i =0; i<decryptedMessage.length(); i++){
            outputFile.write(decryptedMessage.charAt(i));
        }
        outputFile.close();
        System.out.println("Decryption Completed!");
    }
}