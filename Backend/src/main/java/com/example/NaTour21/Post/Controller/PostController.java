package com.example.NaTour21.Post.Controller;

import com.example.NaTour21.Difficulty.Entity.Difficulty;
import com.example.NaTour21.Difficulty.Service.DifficultyService;
import com.example.NaTour21.Duration.Entity.Duration;
import com.example.NaTour21.Duration.Service.DurationService;
import com.example.NaTour21.Post.Entity.Post;
import com.example.NaTour21.Post.Service.PostService;
import com.example.NaTour21.Utils.RequestTemplate.PostRequest;
import com.example.NaTour21.Utils.ResponseTemplate.BasicResponse;
import com.example.NaTour21.Waypoints.Entity.Waypoints;
import com.example.NaTour21.Waypoints.Service.WaypointsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor


public class PostController {
	
	private final PostService postService;
	private final DurationService durationService;
	private final WaypointsService waypointsService;
	private final DifficultyService difficultyService;

	 @GetMapping("/posts")
	 public ResponseEntity<BasicResponse>getPosts(){
		 BasicResponse response = new BasicResponse(postService.getPosts().toArray(),"OK");
		 return ResponseEntity.ok().body(response);
	 }


	 @PostMapping("/post/insert")
	public ResponseEntity<BasicResponse>savePost(@RequestBody PostRequest postRequest){
		 BasicResponse response = null;

		 try {
			 if (postRequest.getDuration() != null && postRequest.getMinutes() != null && postRequest.getDifficulty() != null && postRequest.getUsername()!=null) {

				 Post postSaved = postService.savePost(new Post(postRequest.getTitle(), postRequest.getDescription(), postRequest.getStartpoint(), postRequest.getUsername(), null, null));
				 durationService.saveDuration(new Duration(postRequest.getDuration(), postSaved.getId(), postRequest.getMinutes(), null));
				 difficultyService.saveDifficulty(new Difficulty(postRequest.getDifficulty(), postSaved.getId(), null));
				 waypointsService.saveWaypoints(new Waypoints(postRequest.getLat1(), postRequest.getLng1(), postRequest.getLat2(), postRequest.getLng2(), null, postSaved));
				 response = new BasicResponse(postSaved, "OK");
			 } else {
				 return ResponseEntity.ok().body(new BasicResponse("Campi non validi", "FAILED"));
			 }

			 } catch(Exception e){
				 response = new BasicResponse(e.getMessage(), "FAILED");
			 }

		 return ResponseEntity.ok().body(response);
	 }


}
