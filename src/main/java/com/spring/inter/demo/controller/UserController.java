package com.spring.inter.demo.controller;

import com.spring.inter.demo.bean.User;
import com.spring.inter.demo.result.ResultModelTool;
import com.spring.inter.demo.result.ResultModel;
import com.spring.inter.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/sys")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     *   查询全部
     *   不传参数
     * **/
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResultModel getAllUser(){
        List<User> userList = userService.getAllUser();
        Map<String, List<User>> UserMap = new HashMap<>();
        if (userList != null){
            UserMap.put("users", userList);
        }
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(UserMap);
        return ResultModelTool.handleResultModel(resultModel);
    }

    /**
     *  根据条件查找 get请求
     * **/
    @RequestMapping(value = "/findByNameLike", produces = "application/json;charset=utf-8", method = RequestMethod.GET )
    public ResultModel findByNameLike(String name){
        List<User> userList = userService.findByNameLike(name);
        Map<String, List<User>> UserMap = new HashMap<>();
        if (userList != null){
            UserMap.put("users", userList);
        }
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(UserMap);
        return ResultModelTool.handleResultModel(resultModel);
    }

    /**
     *  条件查询（单表）  post请求
     *
     */
    @PostMapping(value = "/getUser")
    public ResultModel getUser(
        @RequestBody User user) {
        List<User> uList = userService.findAll(user);
        Map<String, List<User>> UserMap = new HashMap<>();
        if (uList != null){
            UserMap.put("users", uList);
        }
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(UserMap);
        return ResultModelTool.handleResultModel(resultModel);
    }


    /**
     *  单个查询
     *  传递参数post请求 （查询条件将会暴漏在url练接后面）
     *
     */
    @RequestMapping(value = "/queryUser", method = RequestMethod.POST)
    public ResultModel queryUser(long id){
        User userList = userService.queryUser(id);
        Map<String, User> UserMap = new HashMap<>();
        if (userList != null){
            UserMap.put("users", userList);
        }
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(UserMap);
        return ResultModelTool.handleResultModel(resultModel);
    }

    /**
     *  添加
     * **/
    @RequestMapping(value = "/addUser", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ResultModel addUser(@RequestBody User user){
        int errorCode = userService.addUser(user);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        resultModel.setData(user);
        return ResultModelTool.handleResultModel(resultModel);
    }

    /**
     *   更新
     * */
    @PostMapping(value = "/updateUser")
    public ResultModel updateUser(@RequestParam long id,@RequestParam String name){
        User User = new User(id,name);
        int errorCode = userService.updateUser(User);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        resultModel.setData(User);
        return ResultModelTool.handleResultModel(resultModel);
    }

    /**
     *  删除
     * **/
    @GetMapping(value = "/deleteUser/{id}")
    public ResultModel deleteUser(@PathVariable long id){
        int errorCode = userService.deleteUser(id);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        List<User> userList = userService.getAllUser();
        Map<String,List<User>> UserMap = new HashMap<>();
        if (userList!=null){
            UserMap.put("users",userList);
        }
        resultModel.setData(UserMap);
        return ResultModelTool.handleResultModel(resultModel);
    }
}
