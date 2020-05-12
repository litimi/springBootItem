/**
 * 实现增删改查
 * **/

package com.spring.inter.demo.service;

import com.spring.inter.demo.bean.User;
import com.spring.inter.demo.repository.JpaUserRepository;
import com.spring.inter.demo.result.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    JpaUserRepository jpaUserRepository;

    @Override
    public List<User> getAllUser(){
        List<User> users = jpaUserRepository.findAll();
        System.out.println(users.toString());
        return users;
    }

    /**
     *  增
     * **/
    @Override
    public int addUser(User user){
        if (jpaUserRepository.existsById(user.getId())){
            System.out.println("user is exsited");
            return ErrorCode.EXISTUSER;
        }
        User saveUser = jpaUserRepository.save(user);
        if (saveUser != null && saveUser.getId() == user.getId()){
            System.out.println("save success");
            return ErrorCode.ADDSUCCESS;
        } else {
            System.out.println("save faild");
            return ErrorCode.ADDFAIL;
        }
    }

    /**
     *  删
     * **/
    @Override
    public int deleteUser(long id){
        if (jpaUserRepository.existsById(id)){
            jpaUserRepository.deleteById(id);
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
    public int updateUser(User user){
        if (jpaUserRepository.existsById(user.getId())){
            jpaUserRepository.save(user);
            System.out.println("更新成功");
            return ErrorCode.UPDATESUCCESS;
        }
        System.out.println("更新失败");
        return ErrorCode.UPDATEFAIL;
    }

    /**
     *  查 get查询
     * **/
    @Override
    public User queryUser(long id) {
        User user = null;
        if (jpaUserRepository.existsById(id)){
            user = jpaUserRepository.findById(id).get();
            System.out.println(user.toString());
        }
        return user;
    }

    /**
     *  条件查询（单表） post （伪条件查询，查询条件在url链接后暴漏出来）
     *
     ***/
    public List findByNameLike(String name) {
        List user = jpaUserRepository.findByNameLike(name + "%");
        return user;
    }

    /**
     *  条件查询（单表） post
     *
     ***/
    @Override
    public List<User> findAll(User user){
        List<User> uList = jpaUserRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (user.getName() != null && !user.getName().equals("")){
                    predicates.add(cb.like(root.get("name").as(String.class), "%" + user.getName() + "%"));
                }
                Predicate[] pre = new Predicate[predicates.size()];
                criteriaQuery.where(predicates.toArray(pre));
                return cb.and(predicates.toArray(pre));
            }
        });
        return uList;
    }
}
