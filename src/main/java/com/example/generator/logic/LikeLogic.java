package com.example.generator.logic;

import com.example.generator.mapper.LikesMapper;
import com.example.generator.model.Likes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LikeLogic {

    @Autowired
    private LikesMapper likesMapper;


    public Likes selectByPrimaryKey(Integer id) {
        return likesMapper.selectByPrimaryKey(id);
    }

    public int insertOne(Likes likes) {
        int result = 0;
        Integer flag = likesMapper.isExist(likes);

        if (flag==null) {
             result = likesMapper.insert(likes);
        }
        return result;
    }


    public int updateOne(Likes likes) {
        int result = 0;
        Integer flag = likesMapper.isExist(likes);

        if (flag!=null&&flag> 0) {
            result = likesMapper.updateByPrimaryKeySelective(likes);
        } else {
            result = 0;
        }
        return result;
    }


    public int deleteOne(Likes likes) {
        int result = 0;
        Likes flag = likesMapper.selectByPrimaryKey(likes.getId());

        if (flag!=null) {
            result = likesMapper.deleteByPrimaryKey(likes.getId());
        } else {
            result = 0;
        }
        return result;
    }

    public List<Likes> queryList(Likes likes) {
        return likesMapper.queryList(likes);
    }



}
