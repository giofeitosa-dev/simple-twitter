package br.com.entities;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "tb_tweets")
public class Tweet {
    
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name = "tweet_id")
    private Long tweetId;
    
    private User user;

    private String content;

    @CreationTimestamp
    private Instant creatInstant;

    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getCreatInstant() {
        return creatInstant;
    }

    public void setCreatInstant(Instant creatInstant) {
        this.creatInstant = creatInstant;
    }



}
