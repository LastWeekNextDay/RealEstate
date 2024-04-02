package dev.lwnd.realestate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RealEstateApplication {

    public static void main(String[] args) {
        if (Global.GOOGLE_API_KEY.isEmpty()){
            if (args.length == 0)
                throw new RuntimeException("Please set your Google API Key in Global.GOOGLE_API_KEY or pass it as an argument.");
            else
                Global.GOOGLE_API_KEY = args[0];
        }

        SpringApplication.run(RealEstateApplication.class, args);
    }

}
