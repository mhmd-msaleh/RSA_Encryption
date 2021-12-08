import java.math.BigInteger;

public class test {
    public static void main(String[] args) {
        BigInteger inverse = new BigInteger("62093").modInverse(new BigInteger((196*198)+""));
        System.out.println(inverse);
    }
}
