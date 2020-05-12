package com.spring.inter.demo.repository;

import com.spring.inter.demo.bean.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaUserRepository extends JpaRepository<User, Long> {
    List findByNameLike(String name);
    List<User> findAll();
    List<User> findAll(Specification<User> spc);
}
