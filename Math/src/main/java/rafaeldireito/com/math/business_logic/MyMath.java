package rafaeldireito.com.math.business_logic;

public class MyMath {

    public static boolean isNumberOdd(int number){return number%2 ==  1;}

    public static boolean isNumberEven(int number){
        return number%2 ==  0;
    }

    public static boolean isPrime(int number){
        if (number > 1000){
            throw new IllegalArgumentException(
                "Wow wow wow... We have no computing resources to " +
                "know if " + number + " is a prime number! " +
                "We can only compute this for numbers <= 1000 !");
        }
        if (number == 1)
            return false;

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if(number%i==0) return false;
        }
        return true;
    }
}
