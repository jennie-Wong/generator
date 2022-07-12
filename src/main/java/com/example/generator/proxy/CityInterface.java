package com.example.generator.proxy;

import com.example.generator.proxy.impl.CityInterfaceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/City")
public class CityInterface {

    @Autowired
    private CityInterfaceImpl cityInterface;

    @PostMapping("/getCity")
    public  String  getCity(@RequestParam("city") String city){
        return cityInterface.getCity(city);
    };
}
