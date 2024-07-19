package com.travel.blog.entity;


import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@Document(collection = "Users")
public class User {

    @Id
    private ObjectId id;

    private String userId;

    private String firstName;

    private String lastName;

    private String emailId;

    private String passWord;




}
