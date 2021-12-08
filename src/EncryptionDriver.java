import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.Scanner;

public class EncryptionDriver{
    public static void main(String[] args) throws Exception{
     RSA rsa = new RSA();
        // String encrypted = rsa.encrypt("STOP", 7912, 13);
        // System.out.println(encrypted);
        // String original = rsa.decrypt("", 7912, 4869);
        // System.out.println(original);
        File inputFile = new File("Encrypt.txt");
        FileOutputStream outputFile = new FileOutputStream("Encrypt.rsa");
        Scanner fScanner = new Scanner(inputFile);
         
        long e = fScanner.nextInt();
        long n = fScanner.nextInt();
        fScanner.nextLine();
        String Message = "";
        // Retrieve the message
        while(fScanner.hasNextLine()){
            Message = Message + fScanner.nextLine();
            if(fScanner.hasNextLine()){
                Message = Message + '\n';
            }
        }
        fScanner.close();

        String encryptedMessage = rsa.encrypt(Message, n, e);



        for(int i = 0; i<encryptedMessage.length(); i++){
            outputFile.write(encryptedMessage.charAt(i));
        }
        outputFile.close();

    }
}