package com.example.generator.Controller;

import com.example.generator.logic.BookDetailLogic;
import com.example.generator.logic.ElementLogic;
import com.example.generator.model.BookDetail;
import com.example.generator.model.Element;
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
import java.util.Date;

/**
 * @author wang
 */
@RestController
@RequestMapping("/Element")
@Log4j
public class ElementController {


    @Value("${file.photo.path}")
    private String uploadPath;


    @Value("${file.photo.getpath}")
    private String getPath;


    @Value("${file.photo.ip}")
    private String ip;

    @Autowired
    private ElementLogic elementLogic;

    @PostMapping("/query")
    public R queryList(@RequestBody Element element) {
        return R.ok(elementLogic.queryList(element));
    }


    @PostMapping("/delete")
    public R deleteBookElement(@RequestBody Element element) {
        if (elementLogic.deleteOne(element) > 0) {
            return new R();
        } else {
            return R.error(500, "此记录不存在");
        }

    }


    @PostMapping("/uploadSc")
    public R uploadFileSc(@RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam("type") String type,
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

            Element element = new Element();
            element.setCreateTime(new Date());
            element.setUrl(filePath);
            element.setType(type);

            elementLogic.insertOne(element);
        } catch (Exception e) {
            log.error("上传文件失败:", e);
        }
        long e1 = System.currentTimeMillis();
        log.info("上传成功");
        return new R().put("URL",filePath);
    }
}