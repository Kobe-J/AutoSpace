package com.crouse.controller;


import com.crouse.model.DeptUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//springboot mybatis 单表增删改查
@RestController
@Api(value = "Dept",description = "First spring mybatis 增删改查")
@RequestMapping("dept")
public class Controller {
    //获取 执行sql 的对象 SqlSessionTemplate
    @Autowired
    private   SqlSessionTemplate sqlSessionTemplate;

    @RequestMapping(value = "/selectCount",method = RequestMethod.GET)
    @ApiOperation(value = "查询用户数量",httpMethod = "GET")
    public int selectCount (){
        return sqlSessionTemplate.selectOne("findUser");
    }

    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    @ApiOperation(value = "查询所有用户信息",httpMethod = "GET")
    public List<DeptUser> selectAll (){ List<DeptUser> d =  sqlSessionTemplate.selectList("findAll");return d; }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ApiOperation(value = "添加用户",httpMethod = "POST")
    public int add(@RequestBody DeptUser deptUser){
        return sqlSessionTemplate.insert("insertUser",deptUser);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation(value ="修改用户信息",httpMethod = "POST")
    public int update(@RequestBody DeptUser deptUser){
        return sqlSessionTemplate.update("upUser",deptUser);
    }

    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户",httpMethod = "DELETE")
    public int del(@RequestParam int deptno){
        return sqlSessionTemplate.delete("del",deptno);
    }

    @RequestMapping(value = "/findone",method = RequestMethod.GET)
    @ApiOperation(value="查询一个用户信息",httpMethod = "GET")
    public List<DeptUser> findone(@RequestParam int deptno){
        return sqlSessionTemplate.selectList("findone",deptno);
    }
}
