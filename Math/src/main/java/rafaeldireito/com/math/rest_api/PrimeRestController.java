package rafaeldireito.com.math.rest_api;


import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rafaeldireito.com.math.business_logic.MyMath;
import rafaeldireito.com.math.schemas.IntegerSchema;
import rafaeldireito.com.math.schemas.MathAPICustomResponse;

@RestController
//prefix to be used in all endpoints that are part of this class
@RequestMapping("${base_api_url}/prime")
@PropertySource("classpath:application.properties")
public class PrimeRestController {
    /*
    SOME CONSIDERATIONS:

     - Since we are using the @RestController annotation, we don't need to specify what
       the format of our response. It defaults to JSON.
       Otherwise, GetMapping(..., produces = "application/json")

     - @ResponseBody is not needed due to using the @RestController annotation

     */

    // GET Request where the number is passed as a path variable
    @GetMapping(value = "/{num}")
    public ResponseEntity<MathAPICustomResponse> primeGetEndpoint(@PathVariable("num") int number) {
        String message =  number + " is NOT a prime number!";
        try {
            if (MyMath.isPrime(number))
                message =  number + " IS a prime number!";
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new MathAPICustomResponse(message,true,"")
            );
        }
        catch(IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new MathAPICustomResponse("An error occurred",false, e.getMessage())
            );
        }
    }

    // POST Request where the number is passed in the POST payload
    @PostMapping(value = {"","/"})
    public ResponseEntity<MathAPICustomResponse> primePostEndpoint(@RequestBody IntegerSchema number) {
        String message =  number.getValue() + " is NOT a prime number!";
        try {
            if (MyMath.isPrime(number.getValue()))
                message =  number.getValue() + " IS a prime number!";
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new MathAPICustomResponse(message,true,"")
            );
        }
        catch(IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new MathAPICustomResponse("An error occurred",false, e.getMessage())
            );
        }
    }


}