package com.travel.blog.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("callBackRequests")
@Data
public class callBackRequests {

    @Id
    private String Id;

    private String userEmail;

    private String phoneNumber;


}
