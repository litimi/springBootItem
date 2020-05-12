package com.spring.inter.demo.service;

import com.spring.inter.demo.bean.Article;

import java.util.List;

public interface IArticleService {
    //获取所有的用户
    List<Article> getAllArticle();

    //增
    int addArticle(Article user);

    //删
    int deleteArticle(long id);

    //改
    int updateArticle(Article article);

    //查
    Article queryArticle(long id);
}
