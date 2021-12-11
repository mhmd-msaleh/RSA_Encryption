import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

public class EncryptionDriver{
    public static void main(String[] args) throws Exception{
        RSA rsa = new RSA();
        File inputFile = new File("files","file.txt");
        FileOutputStream outputFile = new FileOutputStream(new File("files","file.rsa"));
        Scanner fScanner = new Scanner(inputFile);
         
        long e = fScanner.nextLong();
        long n = fScanner.nextLong();
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
        System.out.println("Encryption Completed!");

    }
}