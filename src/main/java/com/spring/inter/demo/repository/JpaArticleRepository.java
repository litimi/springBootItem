package com.spring.inter.demo.repository;

import com.spring.inter.demo.bean.Article;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaArticleRepository extends JpaRepository<Article, Long> {

}

