package com.travel.blog.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.blog.Dao.PostDao;
import com.travel.blog.Model.LikeBody;
import com.travel.blog.entity.Post;
import com.travel.blog.request.PostRequest;
import com.travel.blog.service.IPostService;
import com.travel.blog.service.PostServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class TravelController {

    private final PostDao postDao;

    private final PostServiceImpl postService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public TravelController(PostDao postDao,PostServiceImpl postService){
        this.postDao = postDao;
        this.postService = postService;
    }

    @GetMapping("/getPosts")
    public ResponseEntity<List<Post>> getPosts(){
        return ResponseEntity.ok(postService.getPosts());//postDao.findAll());
    }

    @PostMapping("/post/add")
    public ResponseEntity<Boolean> createPost(@RequestParam String post, @RequestParam MultipartFile image) throws IOException {
        log.info(image.toString());
        log.info(post);
        PostRequest postRequest = objectMapper.readValue(post,PostRequest.class);
        postRequest.setImage(image);
        log.info(postRequest.toString());
        return ResponseEntity.ok(postService.savePost(postRequest));
    }

    @PostMapping("/post/delete")
    public ResponseEntity<Boolean> deletePost(@RequestParam String postId){
        return ResponseEntity.ok(postService.deletePost(postId));
    }

    @PostMapping("/post/like")
    public Boolean likePost(@RequestBody LikeBody likeBody){

        return postService.likePost(likeBody);
    }


//    @PostMapping("update/post")
//    public ResponseEntity<Post> updatePost(@RequestBody Post post){
//        return ResponseEntity.ok(postDao.save(post));
//    }


}
