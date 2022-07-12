package com.example.generator.proxy;

import com.example.generator.Controller.UserController;
import com.example.generator.GeneratorApplication;
import com.example.generator.model.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = GeneratorApplication.class)
@RunWith(SpringRunner.class)
public class JdkCacheHandlerTest {



    @Test
    public void test() {
        CityInterface cityInterface= new CityInterface();
        JdkCacheHandler jdkCacheHandler = new JdkCacheHandler(cityInterface);
        CityInterface proxy = (CityInterface) jdkCacheHandler.createJDKProxy();

        System.out.println("==========================");
        String proxyCity=proxy.getCity("上海");
        System.out.println(proxyCity);
        System.out.println("==========================");

        System.out.println(proxy.getClass());

    }
}