package com.example.generator.proxy;

import com.example.generator.GeneratorApplication;
import com.example.generator.proxy.impl.CityInterfaceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = GeneratorApplication.class)
@RunWith(SpringRunner.class)
public class AdviceTest {

    @Test
    public void test() {

        // 1. 创建代理工厂
        ProxyFactory factory=new ProxyFactory();
        // 2. 设置代理目标对象
        factory.setTarget(new CityInterfaceImpl());
        // 3. 设置接口
        factory.setInterfaces(new Class[]{CityInterface.class});
        // 4. 设置增强
        factory.addAdvice(new LogbeforeCityAdvice());
        // 5. 获取代理对象
        factory.addAdvice(new LogAfterRunningCityAdvice());

        CityInterface cityInterface= (CityInterface) factory.getProxy();
        cityInterface.getCity("北京");

    }
}

