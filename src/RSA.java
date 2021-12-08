import java.io.File;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;

public class RSA {
    private Dictionary dictionary = new Dictionary();

    public RSA() {
    }

    private String translateLettersToNumbers(String text) {
        String numericText = "";
        for (int i = 0; i < text.length(); i++) {
            numericText += dictionary.getValue(text.charAt(i));
        }

        return numericText;
    }

    private int getBlockSize(long N) {
        long divisor = 78;
        while (N > divisor) {
            divisor = divisor * 100 + 78;
        }
        divisor = (long) Math.floor(divisor / 100);
        return (int) (Math.log10(divisor) + 1);
    }

    public String encrypt(String Message, long n, long e) {

        // Variables
        long E = e;
        long N = n;
        String indexedMessage = "";
        String blockMessage = "";
        String encMessage = "";

        // Encryption Process
        // Step1: Translate Plain Text Message into integers
        indexedMessage = translateLettersToNumbers(Message);
        System.out.println(indexedMessage);

        // Step2: Divide the String into equally sized 2N digits
        int blockSize = getBlockSize(N); // 2N digits
        // Step3: Compute Ci and add it to a string encMessage
        while (indexedMessage.length() % blockSize != 0)
            indexedMessage += "78";
        for (int i = 0; i < indexedMessage.length(); i += blockSize) {

            try {
                blockMessage = indexedMessage.substring(i, i + blockSize);
            } catch (IndexOutOfBoundsException ex) {
                while (blockMessage.length() < blockSize) {
                    Random ran = new Random();
                    blockMessage = blockMessage + ran.nextInt(10);
                }
            }
            long blockValue = Integer.parseInt(blockMessage);
            BigInteger C = BigInteger.valueOf(blockValue);
            C = C.modPow(BigInteger.valueOf(E), BigInteger.valueOf(N));
            String temp = C + "";
            while (temp.length() < blockSize) {
                temp = "0" + temp;
            }
            encMessage = encMessage + temp;

            blockMessage = ""; // reset block message to get the next value

        }
        System.out.println("Your Private key 'd' is: " + BigInteger.valueOf(e).modInverse(BigInteger.valueOf(n)));
        return encMessage;
    }

    public String decrypt(String encMessage, long n, long d) {
        Scanner stringScanner = new Scanner(encMessage);

        // String y = max_value +"";
        // blockSize = y.length();
        String decryptedMessage = "";
        String temp = "";
        long passedValue;
        int blockSize = getBlockSize(n);
        // Find M
        for (int k = 0; k < encMessage.length(); k += blockSize) {
            // try{
            passedValue = Long.valueOf(encMessage.substring(k, k + blockSize));
            // } catch (StringIndexOutOfBoundsException ex){
            // passedValue = Long.valueOf(encMessage.substring(k, encMessage.length()));
            // }
            BigInteger M = BigInteger.valueOf(passedValue);
            M = M.modPow(BigInteger.valueOf(d), BigInteger.valueOf(n));
            temp = M + "";
            while (temp.length() % 2 != 0) {
                temp = "0" + temp;
            }
            // int x = Integer.parseInt(temp);
            Scanner blockScanner = new Scanner(temp);
            for (int i = 0; i < temp.length(); i += 2) {
                int numbericChar = Integer.parseInt(temp.substring(i, i + 2));
                System.out.print(numbericChar);
                if (numbericChar > 78)
                    numbericChar %= 79;
                decryptedMessage = decryptedMessage + dictionary.getKey(numbericChar + "");
            }
        }
        stringScanner.close();

        return decryptedMessage;

    }
}

class Dictionary {
    private HashMap<Character, String> alphabet = new HashMap<>();

    public Dictionary() {
        initializeDictionary();
    }

    public int size() {
        return alphabet.size();
    }

    public String getValue(char character) {
        return alphabet.get(character);
    }

    public char getKey(String string) {
        for (Character c : alphabet.keySet())
            if (alphabet.get(c).equals(string))
                return c;
            else
                continue;
        return 0;
    }

    private void initializeDictionary() {
        int i;
        char c;

        for (i = 0, c = 'A'; i < 10; i++, c++)
            alphabet.put(c, "0" + i);
        for (c = c; c <= 'Z'; i++, c++)
            alphabet.put(c, "" + i);
        for (c = 'a'; c <= 'z'; i++, c++)
            alphabet.put(c, "" + i);
        for (c = '0'; c <= '9'; i++, c++)
            alphabet.put(c, "" + i);
        char[] chars = { '.', '?', '!', ',', ';', ':', '-', '(', ')', '{', '}', '[', ']', '\'', '\"', '\n', ' ' };
        for (char character : chars)
            alphabet.put(character, "" + i++);
    }
}
