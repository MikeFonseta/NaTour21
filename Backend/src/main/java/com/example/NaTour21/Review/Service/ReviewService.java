package com.example.NaTour21.Review.Service;

import com.example.NaTour21.Post.Repository.PostRepository;
import com.example.NaTour21.Review.Entity.Review;
import com.example.NaTour21.Review.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional @Slf4j

public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PostRepository postRepository;

    public List<Review> getReviews(){
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByIdPost(Long id_post){
        return reviewRepository.findAllBy(id_post);
    }

    public Review saveReview(Review review) throws Exception{

        if(postRepository.findById(review.getId_post()).isPresent()) {
            if (reviewRepository.findByUsernameAndPost(review.getUsername(), review.getId_post()) != null) {
                throw new Exception("Hai gia' inserito una recensione per questo post");
            } else {
                return reviewRepository.save(review);
            }
        }else
        {
            throw new Exception("Post non trovato");
        }

    }


}
