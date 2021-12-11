import java.math.BigInteger;
import java.util.Random;
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

    private long getBlockSize(long N) {
        long divisor = 78;
        while (N > divisor) {
            divisor = divisor * 100 + 78;
        }
        divisor = (long) Math.floor(divisor / 100);
        return (long) (Math.log10(divisor) + 1);
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

        // Step2: Divide the String into equally sized 2N digits
        long blockSize = getBlockSize(N); // 2N digits
        // Step3: Compute Ci and add it to a string encMessage
        for(int i = 0; i<indexedMessage.length(); i++){
            blockMessage = blockMessage + indexedMessage.charAt(i);    
            if(Math.floorMod(i+1, blockSize) == 0 && i < indexedMessage.length() - (blockMessage.length() - blockSize)){
                long blockValue = Integer.parseInt(blockMessage);  
                BigInteger C = BigInteger.valueOf(blockValue);
                C = C.modPow(BigInteger.valueOf(E), BigInteger.valueOf(N));
                String temp = C +"";
                String temp2 = N +"";
                while(temp.length() < temp2.length()){
                    temp = "0" + temp;
                }  
                encMessage = encMessage + temp;
                blockMessage = ""; //reset block message to get the next value
            }
        }
        // Step4: Assign dummy values to the last block and compute Ci
        if(!blockMessage.isEmpty()){
            while(blockMessage.length() < blockSize){   
                Random ran = new Random();
                blockMessage = blockMessage + ran.nextInt(10);
            }    
            long blockValue = Integer.parseInt(blockMessage);
            BigInteger C = BigInteger.valueOf(blockValue);
            C = C.modPow(BigInteger.valueOf(E), BigInteger.valueOf(N));
            String temp = C +"";
            while(temp.length() < blockSize){
                temp = "0" + temp;
            }   
            encMessage = encMessage + temp;
        }
        return encMessage;
    }

    public String decrypt(String encryptedString, long n, long d) {

        //Variables
        String decryptedMessage = "", nString;
        String blockedString = "", temp ="";
        long blockSize = getBlockSize(n);
        //Dycript all blocks and add them to a string
        nString = n + "";
        for(int i = 0; i < encryptedString.length(); i++){
            blockedString = blockedString + encryptedString.charAt(i);
            if(Math.floorMod(i+1, nString.length()) == 0){
                BigInteger blockedValue = new BigInteger(blockedString);
                BigInteger calculateFME = blockedValue.modPow(BigInteger.valueOf(d), BigInteger.valueOf(n));
                String x = calculateFME + "";
                while(x.length() < blockSize){
                    x = "0" + x;
                } 
                for(int j = 0; j< x.length(); j++){ // Find the character of each two digits of the decrypted value
                    temp += x.charAt(j);
                    if((j+1)%2 == 0 || x.length()==1){
                        decryptedMessage += dictionary.getKey((temp));
                        temp = "";
                    }
                }               
                blockedString = "";
            }
        }
        return decryptedMessage;
    }
}

class Dictionary {
    private HashMap<Character, String> alphabet = new HashMap<>();

    Dictionary() {
        initializeDictionary();
    }

    int size() {
        return alphabet.size();
    }

    String getValue(char character) {
        return alphabet.get(character);
    }

    char getKey(String string) {
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
