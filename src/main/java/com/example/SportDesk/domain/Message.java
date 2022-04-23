package com.example.SportDesk.domain;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity // This tells Hibernate to make a table out of this class
//@Table(name="message")
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message="Введите информацию о мероприятии")
    @Length(max=2048, message="Информация слишком большая (больше 2kB)")
    private String text;
    @Length(max=255, message="Тэг слишком длинный")
    private  String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id")
    private User author;

    private String filename;

    public Message() {
    }

    public Message(String text, String tag, User user) {
        this.author=user;
        this.text = text;
        this.tag = tag;
    }

    public String getAuthorName(){
        return author!=null ? author.getUsername() : "<none>";
    }
    public Long getAuthorId(){
        return author!=null ? author.getId() : -1;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setText(String text){
        this.text=text;
    }

    public String getText() {
        return text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
