package com.example.generator.mapper;

import com.example.generator.model.Likes;
import com.example.generator.model.OperationLog;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface LikesMapper extends Mapper<Likes> {


    Integer isExist(Likes likes);

    List<Likes> queryList(Likes record);

}