package com.travel.blog.service;

import com.travel.blog.Dao.PostDao;
import com.travel.blog.Model.LikeBody;
import com.travel.blog.entity.Post;
import com.travel.blog.request.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class PostServiceImpl implements IPostService{

    @Autowired
    private PostDao postDao;

    public boolean savePost(PostRequest post) throws IOException {
        Post newPost;
        if(post.getPostId()!=null){
            newPost = postDao.findByPostId(post.getPostId()).get(0);
            newPost.setPostTile(post.getPostTile());
            newPost.setCreator(post.getCreator());
            newPost.setImage(post.getImage().getBytes());
            newPost.setInformation(post.getInformation());
            newPost.setLikes(post.getLikes());
            newPost.setTags(post.getTags());
        }
        else{
            newPost  = new Post(post);
        }

        postDao.save(newPost);
        System.out.println(post);
        return true;

    }

    public List<Post> getPosts(){
        return postDao.findAll();
    }

    public Boolean deletePost(String id){
         postDao.deleteByPostId(id);
        return true;
    }

    public Boolean likePost(LikeBody likeBody){
        String userEmaild = likeBody.getUser();
        //Post post = likeBody.getPost();
        Post post = postDao.findByPostId(likeBody.getPost().getPostId()).get(0);

        List<String> likes = post.getLikes();

        if(likes.contains(userEmaild)){
            likes.remove(userEmaild);
        }
        else{
            likes.add(userEmaild);
        }
        post.setLikes(likes);
        postDao.save(post);
        return true;

    }
}
