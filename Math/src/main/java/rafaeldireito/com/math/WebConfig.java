package rafaeldireito.com.math;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// @EnableWebMvc -> Removed to force Spring Boot's MVC autoconfiguration (and therefor the static resource handling)
@ComponentScan(basePackages = "rafaeldireito.com.math")
public class WebConfig {

}