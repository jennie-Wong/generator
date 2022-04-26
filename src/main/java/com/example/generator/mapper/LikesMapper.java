package com.example.generator.mapper;

import com.example.generator.model.Likes;
import com.example.generator.model.OperationLog;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface LikesMapper extends Mapper<Likes> {

    int deleteByPrimaryKey(Integer id);

    int insert(Likes record);

    int insertSelective(Likes record);

    Likes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Likes record);

    int updateByPrimaryKey(Likes record);
    Integer isExist(Likes likes);

    List<Likes> queryList(Likes record);

}