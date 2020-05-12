package com.spring.inter.demo.bean;

//import javax.persistence.Column;
import javax.persistence.*;
import java.io.Serializable;

/**
 *  id
 *  title
 *  type_id
 *  contxt
 *  comment_id
 * **/
@Entity
@Table(name = "art_t")
public class Article implements Serializable {
    @Id
//    @GeneratedValue
    private long id;
    private String title;
    private long type_id;
    private String content;
    private long comment_id;

    public Article(){

    }

    public Article(long id, String title, long type_id, String content, long comment_id){
        this.id = id;
        this.title = title;
        this.type_id = type_id;
        this.content = content;
        this.comment_id = comment_id;
    }


    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public long getType_id(){
        return type_id;
    }
    public  void setType_id(long type_id){
        this.type_id = type_id;
    }

    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }

    public long getComment_id(){
        return comment_id;
    }
    public void setComment_id(long comment_id){
        this.comment_id = comment_id;
    }


    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ",title=" + title +
                ",type_id="+ type_id +
                ",content="+ content +
                ", comment_id=" + comment_id +
                '}';
    }



}
