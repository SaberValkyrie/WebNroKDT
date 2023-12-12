package com.example.imagehacker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;

@SpringBootApplication
public class ImageHackerApplication {

    public static void main(String[] args) {

        SpringApplication.run(ImageHackerApplication.class, args);
        System.out.println("-------------------------------------------------------------");
        Logger.success("[Chạy server thành công vào lúc " + new Timestamp(System.currentTimeMillis()) +"]\n");
        System.out.println("-------------------------------------------------------------");
        int capso = 1;
        try {
            for (int i = capso;i <= capso * 100; i++){
                Thread.sleep(capso  * 1000);
                Logger.setGreen("Server đã chạy được " + i + " giây\n ");
            }
            Logger.setGreen("Server đã chạy được hơn 100 giây\n");
            System.out.println("-------------------------------------------------------------");

        }catch (Exception e){
            Logger.logException(ImageHackerApplication.class,e);
        }

    }
}
