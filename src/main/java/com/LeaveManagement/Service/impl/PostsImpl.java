package com.LeaveManagement.Service.impl;

import com.LeaveManagement.Entity.Grades;
import com.LeaveManagement.Entity.Posts;
import com.LeaveManagement.Repo.GradesRepo;
import com.LeaveManagement.Repo.PostsRepo;
import com.LeaveManagement.Service.EmployeeService;
import com.LeaveManagement.Service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsImpl implements PostsService {

    @Autowired
    private PostsRepo postsRepo;



    @Override
    public Long addPosts(Posts posts) {

       postsRepo.save(posts);
        return posts.getIdPost();
    }



    @Override
    public List<Posts> getAllPosts() {
        return postsRepo.findAll();
    }

    @Override
    public Posts GetPostsById(Long id) {
        return postsRepo.findById(id).get();
    }

    @Override
    public void updatePosts(Long id, Posts post) {
        Posts postsToUpdate = postsRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Post not found"));
        postsToUpdate.setPostNameFr(post.getPostNameFr());
        postsToUpdate.setPostNameAr(post.getPostNameAr());
        postsRepo.save(postsToUpdate);
    }

    @Override
    public void deletePosts(Long id) {
        postsRepo.deleteById(id);

    }
}


