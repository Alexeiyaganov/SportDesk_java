package com.example.SportDesk.domain;

import com.example.SportDesk.domain.util.MessageHelper;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="sportsman")
public class Sportsman {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message="Введите название мероприятии")
    @Length(max=250, message="Информация слишком большая (больше 2kB)")
    private String name;

    @NotBlank(message="Введите название мероприятии")
    @Length(max=250, message="Информация слишком большая (больше 2kB)")
    private String lastname;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id")
    private User author;

    public Sportsman() {
    }

    public Sportsman(String name, String lastname, User user){
        this.name=name;
        this.lastname=lastname;
        this.author=user;
    }

    public String getAuthorName() {
        return MessageHelper.getAuthorName(author);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
