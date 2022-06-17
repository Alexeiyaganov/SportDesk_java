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



}