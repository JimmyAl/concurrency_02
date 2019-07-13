package top.jimmyweb.concurrency02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching//开启缓存
@SpringBootApplication
public class Concurrency02Application {

    public static void main(String[] args) {
        SpringApplication.run(Concurrency02Application.class, args);
    }

}
