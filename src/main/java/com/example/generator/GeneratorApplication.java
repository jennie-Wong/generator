package com.example.generator;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.stream.Stream;

@Slf4j
@SpringBootApplication
@MapperScan("com.example.generator.mapper")
public class GeneratorApplication {


    public static void main(String[] args) {

        long s1 = System.currentTimeMillis();
        SpringApplication.run(GeneratorApplication.class, args);
        long e1 = System.currentTimeMillis();

        log.info("项目启动成功,共计{}秒", (e1 - s1) / 1000);
//        String a="百度(3454)3公司";
//        a=a.replaceAll("\\(|\\)","");
//        System.out.println(a);
//        String b="百度（ewr）3公司";
//        b=b.replaceAll("（","").replaceAll("）","");
//
//        System.out.println(b);
//
//
//        String c="百度（ewr）3公司(456)";
//        c=c.replaceAll("（","").replaceAll("）","").replaceAll("\\(|\\)","");
//
//
//        Stream<Integer> m1 = Stream.of(6, 4, 6, 7, 3, 9, 8, 10, 12, 14, 14);
//        System.out.println("----------------------------------");
//        m1.filter(s -> s > 8||s<5) //6 6 7 9 8 10 12 14 14
//                //   .distinct() //6 7 9 8 10 12 14
//                //  .skip(2) //9 8 10 12 14
//                //  .limit(2) //9 8
//                .forEach(System.out::println);
//        System.out.println(c);

    }

}
