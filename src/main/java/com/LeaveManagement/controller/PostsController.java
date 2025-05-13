package com.LeaveManagement.controller;
import com.LeaveManagement.Entity.Posts;
import com.LeaveManagement.Service.PostsService;
import com.LeaveManagement.Service.impl.PostsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("posts")
public class PostsController {
    @Autowired
    private PostsService postsService;
    @Autowired
    private PostsImpl postsImp;

    @PostMapping(path = "/save")
    public  Long savePosts(@RequestBody Posts posts){

        Long id =postsService.addPosts(posts);
        return id;

    }
    @GetMapping(path="/getAll")
    public List<Posts> getAllPosts(){
        return  postsService.getAllPosts();
    }

    @GetMapping(path="/getById/{Id}")
    public Posts getPostsById(@PathVariable Long Id){
        return postsService.GetPostsById(Id);
    }
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody Posts posts) {
        postsService.updatePosts(id,posts);
        return ResponseEntity.ok("Post updated successfully");
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deletePosts(@PathVariable Long id) {
       postsService.deletePosts(id);
        return ResponseEntity.ok("Post deleted successfully");
    }


}


