import java.util.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;

public class Bonus {
    public static void main(String[] args) throws Exception{
        File input = new File("bonus_files", "p1.rsa");
        Scanner fScanner = new Scanner(input);
        // Scanner kbScanner = new Scanner(System.in);
        // List<String> strings = new ArrayList<>(); 
        // Set<String> dictionary = new HashSet<String>();
        // Scanner filescan = new Scanner(new File("dictionary.txt"));
        // while (filescan.hasNext()) {
        //     dictionary.add(filescan.nextLine().toUpperCase());
        // }
        FileOutputStream outputFile = new FileOutputStream(new File("bonus_files", "p1.dec"));
        long n = 797527;
        RSA rsa = new RSA(); 
        // String string = "This"; 
        // String encypted = rsa.encrypt(string, n, 49);
        String encypted = fScanner.nextLine(); 
        // System.out.println(getEncryptionKeys(n).get(0)); 
        // System.out.println(getDecryptionKey(n).get(0));
        List<Long> keys = getAllDecryptionKeys(n); 
        System.out.println(keys.size());
        System.out.println("Finished getting all keys\n time start from now");
        long time = System.currentTimeMillis();

        
        try(PrintWriter pwriter = new PrintWriter(outputFile);){
            // for(Long key: keys){
                
                
            // }
            pwriter.println(rsa.decrypt(encypted, n, keys.get(100))+"\n");
            
        }
        time = System.currentTimeMillis() - time;
        System.out.println("It took: "+time);
        //     String str = "";
        //    Scanner scanner = new Scanner(new File("bonus_files", "p1.dec")); 
        //     boolean flag = false; 
        //     while (scanner.hasNextLine()){
        //         str = scanner.nextLine(); 
        //         Scanner stringscanner = new Scanner(str); 
        //         while(stringscanner.hasNext()){
        //             if(dictionary.contains(scanner.next())){
        //                 flag = true; 
        //                 break;  
        //             }
        //         } 
        //         if(flag)
        //             break; 
        //     }

        //    System.out.println(str);
        // outputFile.close();
        // System.out.println((7*681943)%getPhi(n));
        // System.out.println(rsa.decrypt(encypted, n, 681943));

        // System.out.println(list); 
        // System.out.println(getPhi(n));
        //System.out.println(getEncryptionKeys(n, getPhi(n)).get(16));
        //System.out.println(getAllCoPrimes(n));
        //System.out.println(getDecryptionKey(2537));
        //long dec = getDecryptionKey(2537); 
        //System.out.println(dec);
        //  BigInteger E, Phi, d; 
        // E = new BigInteger(13+""); 
        // Phi = new BigInteger(getPhi(2537)+""); 
        // d = E.modInverse(Phi);
        // System.out.println(d);
    }

    static List<Long> getAllEncryptionKeys(long n){
        List<Long> possibleKeys = new ArrayList<>();
        long phi = getPhi(n); 
        for(long i = 2; i<phi; i++)
            if(isCoPrime(i, n) && isCoPrime(i, phi))
                possibleKeys.add(i); 
        return possibleKeys; 
    }
    static List<Long> getAllDecryptionKeys(long n){
        List<Long> privateKeys = new ArrayList<>();
        long d = 0, phi = getPhi(n); 
        BigInteger E, Phi, D;
        List<Long> keys = getAllEncryptionKeys(n); 
        for(Long e: keys){
            E = new BigInteger(e+""); 
            Phi = new BigInteger(phi+"");
            D = E.modInverse(Phi);
            if((E.multiply(D)).mod(Phi).longValue() == 1)
                privateKeys.add(D.longValue()); 
        }
        //long e = getEncryptionKey(n, phi); 
        return privateKeys; 

    }

    public static long GCD(long a, long m){
        if(m == 0)
            return a;
        else 
            return GCD(m, a%m); 
    }

    static long getPhi(long n){
        return getAllCoPrimes(n).size(); 
    }

    static boolean isCoPrime(long x, long y){
        return GCD(x, y) == 1; 
    }

    static List<Long> getAllCoPrimes(Long x){
        List<Long> primes = new ArrayList<>(); 
        for (long i = 1; i < x; i++)
            if(isCoPrime(i, x))
                primes.add(i);
        return primes;
            
    }
}
