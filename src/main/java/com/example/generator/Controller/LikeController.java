package com.example.generator.Controller;

import com.example.generator.logic.LikeLogic;
import com.example.generator.model.Likes;
import com.example.generator.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wang
 */
@RestController
@RequestMapping("/Like")
public class LikeController {

    @Autowired
    private LikeLogic LikeLogic;

    @PostMapping("/query")
    public R queryList(@RequestBody Likes likes) {
        return R.ok(LikeLogic.queryList(likes));
    }

    @PostMapping("/add")
    public R addLike(@RequestBody Likes likes) {
        if (LikeLogic.insertOne(likes) > 0) {
            return new R();
        } else {
            return R.error(500, "插入失败");
        }

    }

    @PostMapping("/update")
    public R updateLike(@RequestBody Likes likes) {
        if (LikeLogic.updateOne(likes) > 0) {
            return new R();
        } else {
            return R.error(500, "此记录不存在");
        }

    }

    @PostMapping("/delete")
    public R deleteLike(@RequestBody Likes likes) {
        if (LikeLogic.deleteOne(likes) > 0) {
            return new R();
        } else {
            return R.error(500, "此记录不存在");
        }

    }



}
