package com.adobe.service;

import java.util.List;

import com.adobe.entity.Post;
import com.adobe.exception.PostException;
import com.adobe.exception.UserException;

public interface PostService {
	
	public Post createPost(Post post)throws UserException;

	public Post getPostById(Long id)throws PostException;

	public Post updatePostContent(Long id, String content)throws PostException;

	public void deletePost(Long id)throws PostException;

	public Post likePost(Long id)throws PostException;

	public Post unlikePost(Long id)throws PostException;

	public List<Post> getAllPosts()throws PostException;

}
