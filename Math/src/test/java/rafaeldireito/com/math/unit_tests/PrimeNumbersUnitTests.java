package rafaeldireito.com.math.unit_tests;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import rafaeldireito.com.math.Constants;
import rafaeldireito.com.math.business_logic.MyMath;

import static org.junit.Assert.*;

public class PrimeNumbersUnitTests {

    @Test
    @DisplayName("Test if the Math Module correctly identifies all prime numbers until 1000.")
    public void primeNumbersUntil1000() {
        // check if all prime numbers until 1000 are classified as prime numbers
        for (int number: Constants.primeNumbersUntil1000List){
            assertTrue(MyMath.isPrime(number));
        }
    }

    @Test
    @DisplayName("Test if the Math Module correctly identifies all non-prime numbers until 1000.")
    public void nonPrimeNumbersUntil1000() {
        // check if all non-prime numbers until 1000 are classified as non-prime numbers
        for (int number : Constants.nonPrimeNumbersUntil1000List) {
            assertFalse(MyMath.isPrime(number));
        }
    }

    @Test
    @DisplayName("Test if the Math Module returns an exception when checking if a number > 1000 is prime.")
    public void primeNumbersOver1000() {
        for (int randomNumber : Constants.randomNumberBiggerThan1000){

            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                MyMath.isPrime(randomNumber);
            });

            String expectedMessage = "We can only compute this for numbers <= 1000";
            assertTrue(exception.getMessage().contains(expectedMessage));
        }
    }
}
