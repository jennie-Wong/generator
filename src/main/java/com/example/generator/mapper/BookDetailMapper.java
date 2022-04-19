package com.example.generator.mapper;

import com.example.generator.model.Book;
import com.example.generator.model.BookDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookDetailMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(BookDetail record);

    int insertSelective(BookDetail record);

    BookDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookDetail record);

    int updateByPrimaryKeyWithBLOBs(BookDetail record);

    int updateByPrimaryKey(BookDetail record);
    Integer isExist(Integer id);

    List<BookDetail> queryList(BookDetail record);


}