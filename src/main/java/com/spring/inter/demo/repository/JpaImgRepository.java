package com.spring.inter.demo.repository;

import com.spring.inter.demo.bean.Img;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaImgRepository extends JpaRepository<Img,Integer> {

}
