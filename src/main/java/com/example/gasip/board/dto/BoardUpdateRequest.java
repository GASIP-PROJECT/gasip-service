package com.example.gasip.board.dto;

import com.example.gasip.board.model.Board;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "게시글 수정 Request DTO 관련 VO")
public class BoardUpdateRequest {
    @NotNull(message = "본문은 빈 내용일 수 없습니다.")
    @Schema(description = "게시글 내용")
    private String content;

    public Board toEntity(BoardUpdateRequest boardUpdateRequest) {
        return Board.builder()
                .content(boardUpdateRequest.getContent())
                .build();
    }
}
