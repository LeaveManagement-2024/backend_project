package com.LeaveManagement.Repo;

import com.LeaveManagement.Entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface PostsRepo extends JpaRepository<Posts,Long> {
}
