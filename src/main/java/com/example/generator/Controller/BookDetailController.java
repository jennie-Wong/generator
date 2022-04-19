package com.example.generator.Controller;

import com.example.generator.logic.BookDetailLogic;
import com.example.generator.model.BookDetail;
import com.example.generator.utils.R;
import com.example.generator.utils.UUIDUtils;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author wang
 */
@RestController
@RequestMapping("/BookDetail")
@Log4j
public class BookDetailController {


    @Value("${file.photo.path}")
    private String uploadPath;


    @Value("${file.photo.getpath}")
    private String getPath;


    @Value("${file.photo.ip}")
    private String ip;

    @Autowired
    private BookDetailLogic bookDetailLogic;

    @PostMapping("/query")
    public R queryList(@RequestBody BookDetail detail) {
        return R.ok(bookDetailLogic.queryList(detail));
    }

    @PostMapping("/add")
    public R addBookDetail(@RequestBody BookDetail detail) {
        BookDetail bookDetail = bookDetailLogic.insertOne(detail);
        if (bookDetail != null) {
            return R.ok(bookDetail);
        } else {
            return R.error(500, "新增失败");
        }

    }

    @PostMapping("/update")
    public R updateBookDetail(@RequestBody BookDetail detail) {
        if (bookDetailLogic.updateOne(detail) > 0) {
            return new R();
        } else {
            return R.error(500, "此记录不存在");
        }

    }

    @PostMapping("/delete")
    public R deleteBookDetail(@RequestBody BookDetail detail) {
        if (bookDetailLogic.deleteOne(detail) > 0) {
            return new R();
        } else {
            return R.error(500, "此记录不存在");
        }

    }


    @PostMapping("/uploadold")
    public R upload(@RequestParam MultipartFile file, @RequestParam("id") Integer id) {
        bookDetailLogic.uploadFile(file, id);
        return new R();
    }

    @PostMapping("/download")
    public R download(@RequestParam("id") Integer id, HttpServletResponse response) {

        BookDetail bookDetail = bookDetailLogic.selectByPrimaryKey(id);
        byte[] b = bookDetail.getContent();
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
        return R.ok(bookDetail);
    }



    @PostMapping("/upload")
    public R uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam("id") Integer id,
                           HttpServletRequest request) {
        String url = "";
        Date date = new Date();
        //文件夹路径
        String path = uploadPath + new SimpleDateFormat("yyyy/MM/dd").format(date);
        //如果不存在,创建文件夹
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        // 对上传的文件重命名，避免文件重名
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUIDUtils.getUUID().toString()
                + oldName.substring(oldName.lastIndexOf("."), oldName.length());
        String filePath="";
        try {
            // 文件保存
            uploadFile.transferTo(new File(f, newName));
            // 返回上传文件的访问路径
             filePath = getPath +new SimpleDateFormat("yyyy/MM/dd").format(date)+"/"+ newName;

            BookDetail bookDetail = new BookDetail();
            bookDetail.setId(id);
            bookDetail.setCreateTime(new Date());
            bookDetail.setUrl(filePath);


            bookDetailLogic.savebatch(bookDetail);
        } catch (Exception e) {
            log.error("上传文件失败:", e);
        }
        long e1 = System.currentTimeMillis();
        log.info("上传成功");
        return new R().put("URL",filePath);
    }


    @PostMapping("/uploadSc")
    public R uploadFileSc(@RequestParam("uploadFile") MultipartFile uploadFile,
                        HttpServletRequest request) {
        String url = "";
        Date date = new Date();
        //文件夹路径
        String path = uploadPath +"sucai/"+ new SimpleDateFormat("yyyy/MM/dd").format(date);
        //如果不存在,创建文件夹
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        // 对上传的文件重命名，避免文件重名
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUIDUtils.getUUID().toString()
                + oldName.substring(oldName.lastIndexOf("."), oldName.length());
        String filePath="";
        try {
            // 文件保存
            uploadFile.transferTo(new File(f, newName));
            // 返回上传文件的访问路径
            filePath = getPath +"sucai/"+new SimpleDateFormat("yyyy/MM/dd").format(date)+"/"+ newName;
        } catch (Exception e) {
            log.error("上传文件失败:", e);
        }
        long e1 = System.currentTimeMillis();
        log.info("上传成功");
        return new R().put("URL",filePath);
    }
}