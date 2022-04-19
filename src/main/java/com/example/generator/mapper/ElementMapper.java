package com.example.generator.mapper;

import com.example.generator.model.Element;
import com.example.generator.model.ElementExample;
import com.example.generator.model.Likes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ElementMapper {
    int countByExample(ElementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Element record);

    int insertSelective(Element record);

    Element selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Element record);

    int updateByPrimaryKey(Element record);
    Integer isExist(Integer id);

    List<Element> queryList(Element record);
}