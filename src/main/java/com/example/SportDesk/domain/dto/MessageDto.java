package com.example.SportDesk.domain.dto;

import com.example.SportDesk.domain.Message;
import com.example.SportDesk.domain.User;
import com.example.SportDesk.domain.util.MessageHelper;

import java.sql.Timestamp;


public class MessageDto {
    private Long id;
    private String name;
    private Timestamp date;
    private String text;
    private String tag;
    private User author;
    private String filename;
    private String latitude;
    private String longitude;
    private String location;
    private Timestamp lastupdate;
    private Long likes;
    private Boolean meLiked;


    public MessageDto(Message message, Long likes, Boolean meLiked) {
        this.id = message.getId();
        this.name = message.getName();
        this.text = message.getText();
        this.tag = message.getTag();
        this.date=message.getDate();
        this.author = message.getAuthor();
        this.filename = message.getFilename();
        this.latitude=message.getLatitude();
        this.longitude=message.getLongitude();
        this.location=message.getLocation();
        this.lastupdate=message.getLastupdate();
        this.likes = likes;
        this.meLiked = meLiked;
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Timestamp lastupdate) {
        this.lastupdate = lastupdate;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Boolean getMeLiked() {
        return meLiked;
    }

    public void setMeLiked(Boolean meLiked) {
        this.meLiked = meLiked;
    }
}