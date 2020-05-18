package com.spring.inter.demo.bean;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "img")
public class Img {

    @Id
    @GeneratedValue
    private Integer id;

    private String url;

    public Img(){

    }

    public Img(Integer id, String url){
        this.id = id;
        this.url = url;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    @Override
    public String toString(){
        return "imgPath{"+
                "id="+ id +
                ",url="+ url +
                "}";
    }

}
