package com.per.blog.controller;

import com.per.blog.common.Result;
import com.per.blog.common.StatusCode;
import com.per.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2020/2/26 0026.
 */
@CrossOrigin
@RestController
@RequestMapping("/blog/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(method= RequestMethod.GET)
    public Result findSearch(){
        return  new Result(true, StatusCode.OK,"查询成功",  categoryService.findAll() );
    }

}
