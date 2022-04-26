package com.example.generator;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
public class GeneratorApplication {


    public static void main(String[] args) {

        long s1 = System.currentTimeMillis();
        SpringApplication.run(GeneratorApplication.class, args);
        long e1 = System.currentTimeMillis();

        log.info("项目启动成功,共计{}秒", (e1 - s1) / 1000);
        String a="百度(3454)3公司";
        a=a.replaceAll("\\(|\\)","");
        System.out.println(a);
        String b="百度（ewr）3公司";
        b=b.replaceAll("（","").replaceAll("）","");

        System.out.println(b);


        String c="百度（ewr）3公司(456)";
        c=c.replaceAll("（","").replaceAll("）","").replaceAll("\\(|\\)","");

        System.out.println(c);

    }

}
