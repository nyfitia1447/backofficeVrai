package tp_s6_back;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class TpS6BackApplication {

    @Value("${jasypt.encryptor.password}")
    private String encryptorPassword;

    @Bean(name = "jasyptStringEncryptor")
    public StandardPBEStringEncryptor standardPBEStringEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(encryptorPassword);
        return encryptor;
    }
    public static void main(String[] args) {
        SpringApplication.run(TpS6BackApplication.class, args);
    }

}
