package com.example.generator.mapper;

import com.example.generator.model.Book;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface BookMapper   extends Mapper<Book> {

    Integer isExist(Integer id);

    List<Book> queryList(Book record);
}