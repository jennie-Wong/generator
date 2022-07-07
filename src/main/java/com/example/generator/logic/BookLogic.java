package com.example.generator.logic;
import com.example.generator.mapper.BookMapper;
import com.example.generator.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class BookLogic {

    @Autowired
    private BookMapper bookMapper;


    public Book selectByPrimaryKey(Integer id) {
        return bookMapper.selectByPrimaryKey(id);
    }

    public int insertOne(Book book) {
        int result = bookMapper.insert(book);
        return result;
    }


    public int updateOne(Book book) {
        int result = 0;
        Integer flag = bookMapper.isExist(book.getId());

        if (flag != null && flag > 0) {
            result = bookMapper.updateByPrimaryKeySelective(book);
        } else {
            result = 0;
        }
        return result;
    }


    public int deleteOne(Book book) {
        int result = 0;
        Integer flag = bookMapper.isExist(book.getId());
        if (flag != null && flag > 0) {
            result = bookMapper.deleteByPrimaryKey(book.getId());
        } else {
            result = 0;
        }
        return result;
    }

    public List<Book> queryList(Book book) {
        return bookMapper.queryList(book);
    }

    public void uploadFile(MultipartFile file, Integer id) {
        Book book = new Book();
        book.setId(id);
        try {
            book.setPage(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        bookMapper.updateByPrimaryKeySelective(book);
    }


}
