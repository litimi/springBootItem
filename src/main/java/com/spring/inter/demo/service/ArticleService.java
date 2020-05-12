package com.spring.inter.demo.service;

import com.spring.inter.demo.bean.Article;
import com.spring.inter.demo.repository.JpaArticleRepository;
import com.spring.inter.demo.result.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService implements IArticleService {

    @Autowired
    JpaArticleRepository jpaArticleRepository;

    @Override
    public List<Article> getAllArticle(){
        List<Article> articles = jpaArticleRepository.findAll();
        System.out.println(articles.toString());
        return articles;
    }

    /**
     *  增
     * **/
    @Override
    public int addArticle(Article article){
        if (jpaArticleRepository.existsById(article.getId())){
            System.out.println("article is exsited");
            return ErrorCode.EXISTUSER;
        }
        Article saveArticle = jpaArticleRepository.save(article);
        if (saveArticle != null && saveArticle.getId() == article.getId()){
            System.out.println("save article success");
            return ErrorCode.ADDSUCCESS;
        } else {
            System.out.println("save article faild");
            return ErrorCode.ADDFAIL;
        }
    }

    /**
     *  删
     * **/
    @Override
    public int deleteArticle(long id){
        if (jpaArticleRepository.existsById(id)){
            jpaArticleRepository.deleteById(id);
            System.out.println("删除成功");
            return ErrorCode.DELETESUCCESS;
        }
        System.out.println("删除失败");
        return ErrorCode.NOTEXISTUSER;
    }

    /**
     *  改
     * **/
    @Override
    public int updateArticle(Article article){
        if (jpaArticleRepository.existsById(article.getId())){
            jpaArticleRepository.save(article);
            System.out.println("更新成功");
            return ErrorCode.UPDATESUCCESS;
        }
        System.out.println("更新失败");
        return ErrorCode.UPDATEFAIL;
    }

    /**
     *  查
     * **/
    @Override
    public Article queryArticle(long id) {
        Article article = null;
        if (jpaArticleRepository.existsById(id)){
            article = jpaArticleRepository.findById(id).get();
            System.out.println(article.toString());
        }
        return article;
    }

}
