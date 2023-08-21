package rafaeldireito.com.math.end_to_end_tests.api;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import rafaeldireito.com.math.Constants;
import rafaeldireito.com.math.rest_api.PrimeRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;

@WebMvcTest(PrimeRestController.class)
public class PrimeNumbersRestAPITests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Test if the REST API correctly identifies all prime numbers until 1000.")
    public void primeNumbersUntil1000RestAPI() throws Exception {
        // check if all prime numbers until 1000 are classified as prime numbers
        for (int number: Constants.primeNumbersUntil1000List){
            mockMvc.perform(MockMvcRequestBuilders.get("/api/prime/" + number))
            // validate HTTP Status Code
            .andExpect(MockMvcResultMatchers.status().is(200))
            // validate HTTP Response Type
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            // validate HTTP Response Payload
            .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message",
                Is.is(number + " IS a prime number!")
                )
            );
        }
    }

    @Test
    @DisplayName("Test if the REST API correctly identifies all non-prime numbers until 1000.")
    public void nonPrimeNumbersUntil1000RestAPI() throws Exception {

        // check if all non-prime numbers until 1000 are classified as non-prime numbers
        for (int number: Constants.nonPrimeNumbersUntil1000List){
            mockMvc.perform(MockMvcRequestBuilders.get("/api/prime/" + number))
                // validate HTTP Status Code
                .andExpect(MockMvcResultMatchers.status().is(200))
                // validate HTTP Response Type
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                // validate HTTP Response Payload
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",
                        Is.is(number + " is NOT a prime number!")
                    )
                );
        }
    }

    @Test
    @DisplayName("Test if the REST API returns an exception when checking if a number > 1000 is prime.")
    public void primeNumbersOver1000RestAPI() throws Exception {

        for (int number: Constants.randomNumberBiggerThan1000){
            mockMvc.perform(MockMvcRequestBuilders.get("/api/prime/" + number))
                // validate HTTP Status Code
                .andExpect(MockMvcResultMatchers.status().is(400))
                // validate HTTP Response Type
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                // validate HTTP Response Payload
                .andExpect(MockMvcResultMatchers.jsonPath("$.success", Is.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is("An error occurred")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage",
                    containsString("We can only compute this for numbers <= 1000")
                    )
                );
        }
    }
}
