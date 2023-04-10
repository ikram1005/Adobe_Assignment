package com.adobe.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adobe.entity.Post;
import com.adobe.entity.User;
import com.adobe.exception.PostException;
import com.adobe.exception.UserException;
import com.adobe.repository.PostRepository;
import com.adobe.repository.UserRepository;
import com.adobe.service.PostService;

@Service
public class PostImplementation implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Post createPost(Post post) throws UserException {
		User user = post.getUser();
		Optional<User> userOpt = userRepository.findById(user.getId());
		if (userOpt.isPresent()) {
			post.setCreatedAt();
			post.setUpdatedAt();
			return postRepository.save(post);
		} else {
			throw new UserException("User not found with this id.");
		}
	}

	@Override
	public Post getPostById(Long id) throws PostException {
		Optional<Post> opt = postRepository.findById(id);
	    if (opt.isPresent()) {
	        return opt.get();
	    } else {
	        throw new PostException("Post not found with this id.");
	    }
	}

	@Override
	public Post updatePostContent(Long id, String content) throws PostException {
		Optional<Post> optionalPost = postRepository.findById(id);
	    if (optionalPost.isPresent()) {
	        Post post = optionalPost.get();
	        post.setContent(content);
	        post.setUpdatedAt();
	        return postRepository.save(post);
	    } else {
	        throw new PostException("Post not found with id: " + id);
	    }
	}

	@Override
	public void deletePost(Long id) throws PostException {
		Optional<Post> opt = postRepository.findById(id);
		if (opt.isPresent()) {
			postRepository.deleteById(id);
		} else {
			throw new PostException("Post not found with this id.");
		}
	}

	@Override
	public Post likePost(Long id) throws PostException {
		 Optional<Post> postOpt = postRepository.findById(id);
		    if (postOpt.isPresent()) {
		        Post post = postOpt.get();
		        post.setLikes(post.getLikes() + 1);
		        postRepository.save(post);
		        return post;
		    } else {
		        throw new PostException("Post not found with id: " + id);
		    }
	}

	@Override
	public Post unlikePost(Long id) throws PostException {
		 Optional<Post> postOpt = postRepository.findById(id);
		    if (postOpt.isPresent()) {
		        Post post = postOpt.get();
		        post.setUnlikes(post.getUnlikes()+1);
		        postRepository.save(post);
		        return post;
		    } else {
		        throw new PostException("Post not found with id: " + id);
		    }
	}

	@Override
	public List<Post> getAllPosts() throws PostException {
		List<Post> posts = postRepository.findAll();
		if (posts.size() > 0)
			return posts;
		else
			throw new PostException("Posts not found");
	}

}
