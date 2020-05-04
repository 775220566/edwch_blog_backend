package com.per.blog.controller;

import com.per.blog.common.Result;
import com.per.blog.common.StatusCode;
import com.per.blog.pojo.ArticleComment;
import com.per.blog.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2020/2/11 0011.
 */
@CrossOrigin
@RestController
@RequestMapping("/blog/artComment")
public class ArtCommentController {

    @Autowired
    ArticleCommentService articleCommentService;

    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    public Result findByArtId(@PathVariable String id) {
        return new Result(true, StatusCode.OK,"查询成功",articleCommentService.findByArtId(id));
    }

    @RequestMapping(method=RequestMethod.POST)
    public Result add(@RequestBody ArticleComment articleComment) {
        articleCommentService.add(articleComment);
        return new Result(true, StatusCode.OK, "添加成功");
    }
}
