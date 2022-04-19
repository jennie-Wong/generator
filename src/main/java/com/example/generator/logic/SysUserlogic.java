package com.example.generator.logic;

import com.example.generator.mapper.SysUserMapper;
import com.example.generator.model.SysUser;
import com.example.generator.model.SysUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class SysUserlogic {

    @Autowired
    private SysUserMapper userMapper;


    public SysUser selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(1);
    }

    public int insertOne(SysUser user) {
        int result = 0;
        Integer flag = userMapper.isExist(user.getPhone());

        if (flag==null) {
            result = userMapper.insert(user);
        } else {
            result = 0;
        }
        return result;
    }


    public int updateOne(SysUser user) {
        int result = 0;
        Integer flag = userMapper.selectOneForUpdate(user);

        if (flag!=null&&flag> 0) {
            result = userMapper.updateByPrimaryKeySelective(user);
        } else {
            result = 0;
        }
        return result;
    }


    public int updatePwd(SysUserReq user) {
        int result = 0;
        SysUser newUser=new SysUser();
        newUser.setId(user.getId());
        newUser.setPwd(user.getPwd());
        newUser.setPhone(user.getPhone());
        List<SysUser> users = userMapper.queryList(newUser);

        if (users.size()==1&&user.getOldPwd().equals(users.get(0).getPwd())){
            result = userMapper.updateByPrimaryKeySelective(newUser);
        } else {
            result = 0;
        }
        return result;
    }


    public int deleteOne(SysUser user) {
        int result = 0;
        SysUser flag = userMapper.selectByPrimaryKey(user.getId());

        if (flag!=null) {
            result = userMapper.deleteByPrimaryKey(user.getId());
        } else {
            result = 0;
        }
        return result;
    }

    public List<SysUser> queryList(SysUser user) {
        List<SysUser>  list=userMapper.queryList(user);
        list.stream().forEach(e->e.setSign(e.getSign().replaceAll("！","")));
        return list;
    }

    public void uploadFile(MultipartFile file, Integer id) {
        SysUser user = new SysUser();
        user.setId(id);
        try {
            user.setPhoto(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setSign("hhhhhhh");
        userMapper.updateByPrimaryKeySelective(user);
    }


}
