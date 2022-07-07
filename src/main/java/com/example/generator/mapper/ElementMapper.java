package com.example.generator.mapper;

import com.example.generator.model.Element;
import com.example.generator.model.ElementExample;

import com.example.generator.model.OperationLog;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ElementMapper  extends Mapper<Element> {

    Integer isExist(Integer id);

    List<Element> queryList(Element record);
}