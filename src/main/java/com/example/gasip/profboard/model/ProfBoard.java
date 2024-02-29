package com.example.gasip.profboard.model;

import com.example.gasip.comment.model.Comment;
import com.example.gasip.global.entity.BaseTimeEntity;
import com.example.gasip.member.model.Member;
import com.example.gasip.professor.model.Professor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
@SuperBuilder
@Schema(description = "게시글 관련된 VO")
@DynamicInsert
public class ProfBoard extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "게시글 ID")
    private Long postId;
    @Column(nullable = false,length = 500)
    @Schema(description = "게시글 내용")
    private String content;
    @Column(nullable = false)
    @Schema(description = "게시글 조회수")
    @ColumnDefault("0")
    private Long clickCount;
    @Column(nullable = false)
    @Schema(description = "게시글 좋아요")
    @ColumnDefault("0")
    private Long likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prof_id")
    @Schema(description = "게시글과 관련된 교수정보")
    private Professor professor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "profBoard", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public ProfBoard(LocalDateTime regDate, LocalDateTime updateDate, Long postId, String content, Long clickCount, Long likeCount, Professor professor, Member member) {
        super(regDate, updateDate);
        this.postId = postId;
        this.content = content;
        this.clickCount = clickCount;
        this.likeCount = likeCount;
        this.professor = professor;
        this.member = member;
    }
    public void updateBoard(String content) {
        this.content = content;
        this.updateDate = LocalDateTime.now();
    }
}