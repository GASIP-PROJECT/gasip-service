package com.example.gasip.profboard.dto;

import com.example.gasip.profboard.model.ProfBoard;
import com.example.gasip.global.entity.BaseTimeEntity;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@SuperBuilder
@Schema(description = "게시글 읽기 Response DTO 관련 VO")
@AllArgsConstructor
public class BoardReadResponse extends BaseTimeEntity {
    @NotNull
    @Schema(description = "게시글 ID")
    private Long postId;
    @NotNull
    @Schema(description = "게시글 내용")
    private String content;
    @Schema(description = "게시글 조회수")
    private Long clickCount;
    @Schema(description = "게시글 좋아요")
    private Long likeCount;
    @NotNull
    @Schema(description = "게시글과 관련된 교수 정보")
    private Long profId;

    @QueryProjection
    public BoardReadResponse(LocalDateTime regDate, LocalDateTime updateDate, Long postId, String content, Long clickCount, Long likeCount, Long profId) {
        super(regDate, updateDate);
        this.postId = postId;
        this.content = content;
        this.clickCount = clickCount;
        this.likeCount = likeCount;
        this.profId = profId;
    }
    public static BoardReadResponse fromEntity(ProfBoard profBoard) {
        return BoardReadResponse.builder()
                .regDate(profBoard.getRegDate())
                .updateDate(profBoard.getUpdateDate())
                .postId(profBoard.getPostId())
                .content(profBoard.getContent())
                .clickCount(profBoard.getClickCount())
                .likeCount(profBoard.getLikeCount())
                .profId(profBoard.getProfessor().getProfId())
                .build();
    }
}
