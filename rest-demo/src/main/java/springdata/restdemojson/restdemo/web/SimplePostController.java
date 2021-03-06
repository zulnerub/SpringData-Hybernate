package springdata.restdemojson.restdemo.web;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import springdata.restdemojson.restdemo.model.Post;
import springdata.restdemojson.restdemo.service.PostService;

import javax.annotation.PostConstruct;
import java.net.URI;

@RestController
@RequestMapping("/api/simple")
@Slf4j
public class SimplePostController {
    @Autowired
    private PostService postService;

    private Gson gson;

    @PostConstruct
    private void init(){
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPosts(){
        return gson.toJson(postService.getPosts());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addPost(@RequestBody String body){
        log.info("Body received: {}", body);
        Post post = gson.fromJson(body, Post.class);
        log.info("Post de-serialized: {}", post);
        Post createdPost = postService.addPost(post);
        URI uri = MvcUriComponentsBuilder
                .fromMethodName(SimplePostController.class,"addPost", post)
                .pathSegment("{id}").buildAndExpand(createdPost.getId()).toUri();
        return ResponseEntity.created(uri).body(gson.toJson(createdPost));
    }

}
