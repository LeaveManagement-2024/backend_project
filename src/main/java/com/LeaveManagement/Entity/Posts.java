package com.LeaveManagement.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "Posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdPost;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String postName;


    public Posts() {
    }

    public Posts(Long idPost, String postNameFr) {
        IdPost = idPost;
        this.postName = postNameFr;

    }

    public Posts(Long idPost) {
        IdPost = idPost;
    }

    public Long getIdPost() {
        return IdPost;
    }

    public void setIdPost(Long idPost) {
        IdPost = idPost;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postNameFr) {
        this.postName = postNameFr;
    }


}
