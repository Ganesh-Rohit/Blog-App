package com.travel.blog.Dao;

import com.travel.blog.entity.Post;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDao extends MongoRepository<Post,Integer> {

    public void deleteByPostId(String id);

    List<Post> findByPostId(String postId);

    Post findById(ObjectId id);
}
