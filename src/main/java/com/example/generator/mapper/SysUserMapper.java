package com.example.generator.mapper;

import com.example.generator.model.Likes;
import com.example.generator.model.SysUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface SysUserMapper extends Mapper<SysUser> {


    Integer isExist(String phone);

    Integer selectOneForUpdate(SysUser record);

    List<SysUser> queryList(SysUser record);
}