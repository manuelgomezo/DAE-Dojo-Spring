package co.edu.udea.dojospring.controller;


import co.edu.udea.dojospring.exception.ResourceNotFoundException;
import co.edu.udea.dojospring.model.Post;
import co.edu.udea.dojospring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostController{

    @Autowired
    PostRepository post;


    @GetMapping("/posts")
    public List<Post> getAllPost(){
        return post.findAll();
    }

    @PostMapping("/post")
    public Post createPost(@Valid @RequestBody Post post) {
        return this.post.save(post);
    }

    @GetMapping("/post/{id}")
    public Post getPostById(@PathVariable(value = "id") Long postId) {
        return post.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
    }

    @PutMapping("/post/{id}")
    public Post updatePost(@PathVariable(value = "id") Long postId,
                           @Valid @RequestBody Post postDetails) {

        Post postNote = post.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        postNote.setTitle(postDetails.getTitle());
        postNote.setContent(postDetails.getContent());

        Post updatedPost = post.save(postNote);
        return updatedPost;
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(value = "id") Long postId) {
        Post post = this.post.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        this.post.delete(post);

        return ResponseEntity.ok().build();
    }
}
