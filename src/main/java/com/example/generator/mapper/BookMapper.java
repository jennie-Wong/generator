package com.example.generator.mapper;

import com.example.generator.model.Book;
import com.example.generator.model.SysUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface BookMapper   extends Mapper<Book> {

    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book record);

    Integer isExist(Integer id);

    List<Book> queryList(Book record);
}