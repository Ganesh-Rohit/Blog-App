package com.travel.blog.entity;


import com.travel.blog.request.PostRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Document(collection = "Posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @Field("_id")
    private ObjectId  id;

    private String postId;

    //private String ImageUrl;
    private byte[] image;
    private String creator;
    //private MultipartFile Image;

    private String createdBy;

    private String postTile;

    private String information;

    @BsonProperty("likes")
    private List<String> likes;

    @BsonProperty("post_tags")
    private List<String> tags;

    public Post(PostRequest postRequest) throws IOException {
        this.creator = postRequest.getCreator();
        this.image = postRequest.getImage().getBytes();
        this.information = postRequest.getInformation();
        this.likes = postRequest.getLikes();
        this.createdBy =postRequest.getCreatedBy();
        if(postRequest.getPostId()==null)
        this.postId = UUID.randomUUID().toString();
        else{
            this.postId = postRequest.getPostId();
            this.id = postRequest.getId();
        }
        this.postTile = postRequest.getPostTile();
        this.tags = postRequest.getTags();
    }
}
