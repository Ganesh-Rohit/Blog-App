package com.travel.blog.request;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostRequest {


    private ObjectId id;

    private String postId;

    private String creator;

    private MultipartFile image;

    private String postTile;

    private String information;

    private String createdBy;

    private List<String> tags;

    private List<String> likes;

}
