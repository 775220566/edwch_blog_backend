package com.per.blog.controller;

import com.per.blog.common.PageResult;
import com.per.blog.common.Result;
import com.per.blog.common.StatusCode;
import com.per.blog.pojo.Article;
import com.per.blog.pojo.ArticleListVO;
import com.per.blog.service.ArticleService;
import com.per.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Administrator on 2020/2/9 0009.
 */
@CrossOrigin
@RestController
@RequestMapping("/blog/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;

    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    public Result findById(@PathVariable String id){
        Article article = articleService.findById(id);
        return new Result(true, StatusCode.OK,"查询成功",article);
    }

    @RequestMapping(value="/search/{page}/{size}",method=RequestMethod.GET)
    public Result findSearch(@RequestParam Map searchMap , @PathVariable int page, @PathVariable int size){
        Page<ArticleListVO> pageList = articleService.findByMapPage(searchMap, page, size);
        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<ArticleListVO>(pageList.getTotalElements(), pageList.getContent()) );
    }

    @RequestMapping(method=RequestMethod.POST)
    public Result add(@RequestBody Article article) {
        articleService.add(article);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value="/{id}",method= RequestMethod.PUT)
    public Result update(@RequestBody Article article, @PathVariable String id ){
        article.setId(id);
        articleService.update(article);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @RequestMapping(value="/hot",method= RequestMethod.GET)
    public Result findHot(){
        return new Result(true, StatusCode.OK,"查询成功", articleService.findHot());
    }

    @RequestMapping(value="/latest",method= RequestMethod.GET)
    public Result findLatest(){
        return new Result(true, StatusCode.OK,"查询成功",articleService.findLatest());
    }

}
