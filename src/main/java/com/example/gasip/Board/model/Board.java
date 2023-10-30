package com.example.gasip.Board.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @Column(nullable = false,length = 500)
    private String content;
    @Column(nullable = true)
    private Long clickCount;
    @Column(nullable = true)
    private Long likeCount;
    @Column(nullable = false)
    private LocalDateTime regDate;
    @Column(nullable = false)
    private LocalDateTime updateDate;
    @Column(nullable = false)
    private Long profId;

    @Builder
    public Board(Long postId, String content, Long clickCount, Long likeCount, LocalDateTime regDate, LocalDateTime updateDate, Long profId) {
        this.postId = postId;
        this.content = content;
        this.clickCount = clickCount;
        this.likeCount = likeCount;
        this.regDate = regDate;
        this.updateDate = updateDate;
        this.profId = profId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board that)) return false;
        return postId != null && postId.equals(that.getPostId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }
}