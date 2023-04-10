package com.adobe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adobe.entity.Post;
import com.adobe.exception.PostException;
import com.adobe.exception.UserException;
import com.adobe.service.PostService;

@RestController
public class PostController {

	@Autowired
    private PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) throws UserException,PostException {
        Post createdPost = postService.createPost(post);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) throws PostException {
        Post post = postService.getPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePostContent(@PathVariable Long id, @RequestBody Post post) throws PostException {
        Post updatedPost = postService.updatePostContent(id, post.getContent());
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) throws PostException {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/posts/{id}/like")
    public ResponseEntity<Post> likePost(@PathVariable Long id) throws PostException {
        Post post = postService.likePost(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping("/posts/{id}/unlike")
    public ResponseEntity<Post> unlikePost(@PathVariable Long id) throws PostException {
        Post post = postService.unlikePost(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    
    @GetMapping("/Allposts")
    public ResponseEntity<List<Post>> getAllPosts() throws PostException{
    	List<Post>posts=postService.getAllPosts();
    	return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
    }
}
