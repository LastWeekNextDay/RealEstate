package dev.lwnd.realestate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RealEstateApplication {

    public static void main(String[] args) {
        if (Global.GOOGLE_API_KEY.isEmpty())
            throw new RuntimeException("Please set your Google API Key in Global.GOOGLE_API_KEY");
        SpringApplication.run(RealEstateApplication.class, args);
    }

}
