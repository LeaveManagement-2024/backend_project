package com.LeaveManagement.Service;

import com.LeaveManagement.Entity.Employees;
import com.LeaveManagement.Entity.Posts;

import java.util.List;

public interface PostsService {
    Long addPosts(Posts posts);
    public List<Posts> getAllPosts();
    public Posts GetPostsById(Long  id);
    void updatePosts(Long id, Posts post);
    void deletePosts(Long id);
}
