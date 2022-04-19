package com.example.generator.logic;


import com.example.generator.mapper.BookDetailMapper;
import com.example.generator.mapper.ElementMapper;
import com.example.generator.model.BookDetail;
import com.example.generator.model.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class ElementLogic {

    @Autowired
    private ElementMapper elementMapper;


    public Element selectByPrimaryKey(Integer id) {
        return elementMapper.selectByPrimaryKey(id);
    }

    public Element insertOne(Element element) {
        int result = elementMapper.insert(element);
        return elementMapper.selectByPrimaryKey(element.getId());
    }


    public int deleteOne(Element element) {
        int result = 0;
        Integer flag = elementMapper.isExist(element.getId());

        if (flag != null && flag > 0) {
            result = elementMapper.deleteByPrimaryKey(element.getId());
        } else {
            result = 0;
        }
        return result;
    }

    public List<Element> queryList(Element element) {
        return elementMapper.queryList(element);
    }


}
