import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * decryption
 */
public class DecryptionDriver {

    public static void main(String[] args) throws IOException {
        //Scanners
        File input = new File("files", "file.rsa");
        Scanner fScanner = new Scanner(input);
        Scanner kbScanner = new Scanner(System.in);
        FileOutputStream outputFile = new FileOutputStream(new File("files", "file.dec"));

        RSA rsa = new RSA();

        //Variables
        String decryptedMessage = "";
        long d, n;

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
        fScanner.close();
        System.out.println("Decryption Completed!");
    }
}