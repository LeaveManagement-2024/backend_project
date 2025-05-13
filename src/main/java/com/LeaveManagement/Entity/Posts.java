package com.LeaveManagement.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "Posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdPost;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String postNameFr;
    @Column(columnDefinition = "NVARCHAR(255)")
    private String postNameAr;

    public Posts() {
    }

    public Posts(Long idPost, String postNameFr, String postNameAr) {
        IdPost = idPost;
        this.postNameFr = postNameFr;
        this.postNameAr = postNameAr;
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

    public String getPostNameFr() {
        return postNameFr;
    }

    public void setPostNameFr(String postNameFr) {
        this.postNameFr = postNameFr;
    }

    public String getPostNameAr() {
        return postNameAr;
    }

    public void setPostNameAr(String postNameAr) {
        this.postNameAr = postNameAr;
    }
}
