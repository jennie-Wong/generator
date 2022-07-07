package com.example.generator.mapper;

import com.example.generator.model.Book;
import com.example.generator.model.BookDetail;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface BookDetailMapper extends Mapper<BookDetail> {

    Integer isExist(Integer id);

    List<BookDetail> queryList(BookDetail record);


}