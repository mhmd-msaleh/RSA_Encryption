import java.math.BigInteger;

public class test {
    public static void main(String[] args) {
        BigInteger inverse = new BigInteger("57").modInverse(new BigInteger("7972"));
        System.out.println(inverse);
    }
}
