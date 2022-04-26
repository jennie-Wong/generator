package com.example.generator.Controller;

import com.example.generator.anno.WriteOperLog;
import com.example.generator.logic.SysUserlogic;
import com.example.generator.model.SysUser;
import com.example.generator.model.SysUserReq;
import com.example.generator.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author wang
 */
@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private SysUserlogic userlogic;

    @WriteOperLog(description = "查询用户列表")
    @PostMapping("/query")
    public R queryList(@RequestBody SysUser user) {

        return R.ok(userlogic.queryList(user));
    }

    @PostMapping("/add")
    public R addUser(@RequestBody SysUser user) {
        if (userlogic.insertOne(user) > 0) {
            return new R();
        } else {
            return R.error(500, "此手机号已注册");
        }

    }

    @PostMapping("/update")
    public R updateUser(@RequestBody SysUser user) {
        if (userlogic.updateOne(user) > 0) {
            return new R();
        } else {
            return R.error(500, "此用户不存在");
        }

    }


    @PostMapping("/updatePwd")
    public R updatePwd(@RequestBody SysUserReq user) {
        if (userlogic.updatePwd(user) > 0) {
            return new R();
        } else {
            return R.error(500, "此用户不存在");
        }

    }

    @PostMapping("/delete")
    public R deleteUser(@RequestBody SysUser user) {
        if (userlogic.deleteOne(user) > 0) {
            return new R();
        } else {
            return R.error(500, "此用户不存在");
        }

    }


    @PostMapping("/upload")
    public R upload(@RequestParam MultipartFile file, @RequestParam("id") Integer id) {
        userlogic.uploadFile(file, id);
        return new R();
    }

    @PostMapping("/download")
    public R download(@RequestParam("id") Integer id, HttpServletResponse response) {

        SysUser sysUser = userlogic.selectByPrimaryKey(id);
        byte[] b = sysUser.getPhoto();
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
        return  R.ok(sysUser);
    }

    @PostMapping("/login")
    public R login(@RequestBody SysUser user) {
      List<SysUser> users= userlogic.queryList(user);

        if (users==null){
            return R.error(500, "没有此用户");
        }else if (users.size()==1&&user.getPwd().equals(users.get(0).getPwd())){
            return R.ok(users.get(0));

        }else{
            return R.error(500, "登录失败");

        }



    }

}
