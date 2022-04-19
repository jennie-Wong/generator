package com.example.generator.logic;


import com.example.generator.mapper.BookDetailMapper;
import com.example.generator.model.BookDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookDetailLogic {

    @Autowired
    private BookDetailMapper bookDetailMapper;


    public BookDetail selectByPrimaryKey(Integer id) {
        return bookDetailMapper.selectByPrimaryKey(id);
    }

    public BookDetail insertOne(BookDetail bookDetail) {
        int result = bookDetailMapper.insert(bookDetail);
        return bookDetailMapper.selectByPrimaryKey(bookDetail.getId());
    }


    public int updateOne(BookDetail bookDetail) {
        int result = 0;
        Integer flag = bookDetailMapper.isExist(bookDetail.getId());

        if (flag != null && flag > 0) {
            result = bookDetailMapper.updateByPrimaryKeySelective(bookDetail);
        } else {
            result = 0;
        }
        return result;
    }


    public int deleteOne(BookDetail bookDetail) {
        int result = 0;
        Integer flag = bookDetailMapper.isExist(bookDetail.getId());

        if (flag != null && flag > 0) {
            result = bookDetailMapper.deleteByPrimaryKey(bookDetail.getId());
        } else {
            result = 0;
        }
        return result;
    }

    public List<BookDetail> queryList(BookDetail bookDetail) {
        return bookDetailMapper.queryList(bookDetail);
    }

    public void uploadFile(MultipartFile file, Integer id) {
        BookDetail bookDetail = new BookDetail();
        bookDetail.setId(id);
        try {
            bookDetail.setContent(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        bookDetailMapper.updateByPrimaryKeySelective(bookDetail);
    }


    public int savebatch(BookDetail bookDetail) {
        int result = 0;


        Integer flag = bookDetailMapper.isExist(bookDetail.getId());

        if (flag != null && flag > 0) {
            result = bookDetailMapper.updateByPrimaryKeySelective(bookDetail);
        } else {
            result = 0;
        }

        return result;
    }
}
