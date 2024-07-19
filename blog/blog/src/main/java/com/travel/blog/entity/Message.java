package com.travel.blog.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Messages")
public class Message {

    @Id
    private String Id;



}
