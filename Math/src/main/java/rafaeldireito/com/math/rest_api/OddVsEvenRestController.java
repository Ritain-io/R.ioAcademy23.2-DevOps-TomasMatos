package rafaeldireito.com.math.rest_api;


import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rafaeldireito.com.math.business_logic.MyMath;
import rafaeldireito.com.math.schemas.MathAPICustomResponse;
import rafaeldireito.com.math.schemas.IntegerSchema;

@RestController
//prefix to be used in all endpoints that are part of this class
@RequestMapping("${base_api_url}/odd-vs-even")
@PropertySource("classpath:application.properties")
public class OddVsEvenRestController {
    /*
    SOME CONSIDERATIONS:

     - Since we are using the @RestController annotation, we don't need to specify what
       the format of our response. It defaults to JSON.
       Otherwise, GetMapping(..., produces = "application/json")

     - @ResponseBody is not needed due to using the @RestController annotation

     */

    // GET Request where the number is passed as a path variable
    @GetMapping(value = "/{num}")
    public ResponseEntity<MathAPICustomResponse> oddVsEvenGetEndpoint(@PathVariable("num") int number) {
        String message = "Number is even!";

        if (MyMath.isNumberOdd(number)) {
            message = "Number is odd!";
        }
        return  ResponseEntity.status(HttpStatus.OK).body(
                new MathAPICustomResponse(message,true,"")
        );
    }

    // POST Request where the number is passed in the POST payload
    @PostMapping(value = {"","/"})
    public ResponseEntity<MathAPICustomResponse> oddVsEvenPostEndpoint(@RequestBody IntegerSchema number) {
        String message = "Number is even!";

        if (MyMath.isNumberOdd(number.getValue())) {
            message = "Number is odd!";
        }
        return  ResponseEntity.status(HttpStatus.OK).body(
                new MathAPICustomResponse(message,true,"")
        );
    }


}