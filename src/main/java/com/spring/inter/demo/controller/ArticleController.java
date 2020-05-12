package com.spring.inter.demo.controller;


import com.spring.inter.demo.bean.Article;
import com.spring.inter.demo.result.ResultModelTool;
import com.spring.inter.demo.result.ResultModel;
import com.spring.inter.demo.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/sys")
public class ArticleController {
    @Autowired
    IArticleService articleService;

    /**
     *   查询全部
     *   不传参数
     * **/
    @RequestMapping(value = "/allarticle", method = RequestMethod.GET)
    public ResultModel getAllArticle(){
        List<Article> articleList = articleService.getAllArticle();
        Map<String, List<Article>> ArticleMap = new HashMap<>();
        if (articleList != null){
            ArticleMap.put("artiles", articleList);
        }
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(ArticleMap);
        return ResultModelTool.handleResultModel(resultModel);
    }

    /**
     *  单个查询
     *  传递参数post请求
     *
     */
    @RequestMapping(value = "/queryArticle", method = RequestMethod.POST)
    public ResultModel queryArticle(long id){
        Article articleList = articleService.queryArticle(id);
        Map<String, Article> ArticleMap = new HashMap<>();
        if (articleList != null){
            ArticleMap.put("articles", articleList);
        }
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(ArticleMap);
        return ResultModelTool.handleResultModel(resultModel);
    }

    /**
     *  添加
     *  @RequestBody post请求注解
     * **/
    @RequestMapping(value = "/addArticle", produces = "application/json;charset=utf-8", method = RequestMethod.POST )
    public ResultModel addArticle(@RequestBody Article article){
        System.out.println(article.toString());
        int errorCode = articleService.addArticle(article);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        resultModel.setData(article);
        return ResultModelTool.handleResultModel(resultModel);
    }

    /**
     *   更新
     * */
    @RequestMapping(value = "/updateArticle", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ResultModel updateArticle(@RequestBody Article article){
        int errorCode = articleService.updateArticle(article);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        resultModel.setData(article);
        return ResultModelTool.handleResultModel(resultModel);
    }

    /**
     *  删除接口
     * **/
    @GetMapping(value = "/deleteArticle/{id}")
    public ResultModel deleteArticle(@PathVariable long id){
        int errorCode = articleService.deleteArticle(id);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        List<Article> userList = articleService.getAllArticle();
        Map<String,List<Article>> ArticleMap = new HashMap<>();
        if (userList!=null){
            ArticleMap.put("articles",userList);
        }
        resultModel.setData(ArticleMap);
        return ResultModelTool.handleResultModel(resultModel);
    }
}
