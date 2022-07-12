package com.example.generator.proxy;

import com.example.generator.GeneratorApplication;
import org.apache.catalina.core.ApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = GeneratorApplication.class)
@RunWith(SpringRunner.class)
public class AspectTest {

    @Autowired

    @Test
    public void test() {


    }
}

