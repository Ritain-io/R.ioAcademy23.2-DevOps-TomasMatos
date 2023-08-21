package rafaeldireito.com.math;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI SwaggerConfig() {
        Server mServer = new Server();
        mServer.setDescription("Default Math Application Server");



        Contact contact = new Contact();
        contact.setName("Rafael Direito");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Math API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage Math services.")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(mServer));
    }
}