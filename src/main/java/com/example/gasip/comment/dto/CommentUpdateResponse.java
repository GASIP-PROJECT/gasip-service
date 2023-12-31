package com.example.gasip.comment.dto;

import com.example.gasip.comment.model.Comment;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CommentUpdateResponse {
    private String content;

    public static CommentUpdateResponse fromEntity(Comment comment) {
        return CommentUpdateResponse.builder()
            .content(comment.getContent())
            .build();
    }
}
