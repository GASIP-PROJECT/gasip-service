package com.example.gasip.comment.dto;

import com.example.gasip.comment.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CommentDto implements Serializable {
    private Long postId;
    private Long memberId;
    private String content;
    private Long commentLike;
    private Long parentId;
    private List<CommentDto> commentChildren = new ArrayList<>();

    public static CommentDto fromEntity(Comment comment) {
        // 부모댓글이 있는 경우
        if (comment.getParentComment() != null) {
            return CommentDto.builder()
                .postId(comment.getBoard().getPostId())
                .memberId(comment.getMember().getMemberId())
                .content(comment.getContent())
                .parentId(comment.getParentComment().getCommentId())
                .build();
        }
        // 부모댓글이 없는 경우
        else {
            return CommentDto.builder()
                .postId(comment.getBoard().getPostId())
                .memberId(comment.getMember().getMemberId())
                .content(comment.getContent())
                .commentChildren(comment.getCommentChildren().stream().map(CommentDto::fromEntity).collect(Collectors.toList()))
                .build();
        }

    }
}
