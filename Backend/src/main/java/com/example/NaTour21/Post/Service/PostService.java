package com.example.NaTour21.Post.Service;

import com.example.NaTour21.Post.Entity.Post;
import com.example.NaTour21.Post.Repository.PostRepository;
import com.example.NaTour21.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class PostService {
	
	private final PostRepository postRepository;
	private final UserRepository userRepository;

	public List<Post> getPosts(){
		return postRepository.findAllByOrderByIdDesc();

	}

	public Post getPostById(Long id) {
		return postRepository.findById(id).get();
	}

	public Post savePost(Post post) throws Exception {

		if(userRepository.findByUsername(post.getUsername())==null){
			throw new Exception("Username non registrato");
		}
		else if(post.getStartpoint()==null || post.getTitle()==null){
			throw new Exception("Campi non validi");
		}else
		{
			return postRepository.save(post);
		}

	}
}
