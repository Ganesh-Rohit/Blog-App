package com.travel.blog.Model;

import com.travel.blog.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeBody {

    private String user;

    private Post post;




}
