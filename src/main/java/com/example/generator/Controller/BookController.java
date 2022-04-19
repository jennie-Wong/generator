package com.example.generator.Controller;

import com.example.generator.logic.BookLogic;
import com.example.generator.model.Book;
import com.example.generator.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wang
 */
@RestController
@RequestMapping("/Book")
public class BookController {

    @Autowired
    private BookLogic bookLogic;

    @PostMapping("/query")
    public R queryList(@RequestBody Book user) {
        return R.ok(bookLogic.queryList(user));
    }

    @PostMapping("/add")
    public R addBook(@RequestBody Book book) {
        if (bookLogic.insertOne(book) > 0) {
            return new R();
        } else {
            return R.error(500, "新增失败");
        }

    }

    @PostMapping("/update")
    public R updateBook(@RequestBody Book book) {
        if (bookLogic.updateOne(book) > 0) {
            return new R();
        } else {
            return R.error(500, "此记录不存在");
        }

    }

    @PostMapping("/delete")
    public R deleteBook(@RequestBody Book book) {
        if (bookLogic.deleteOne(book) > 0) {
            return new R();
        } else {
            return R.error(500, "此记录不存在");
        }

    }


    @PostMapping("/upload")
    public R upload(@RequestParam MultipartFile file, @RequestParam("id") Integer id) {
        bookLogic.uploadFile(file, id);
        return new R();
    }

    @PostMapping("/download")
    public R download(@RequestParam("id") Integer id, HttpServletResponse response) {

        Book book = bookLogic.selectByPrimaryKey(id);
        byte[] b = book.getPage();
        InputStream inputStream = new ByteArrayInputStream(b);
        response.setContentType("image/*");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  R.ok(book);
    }

}
