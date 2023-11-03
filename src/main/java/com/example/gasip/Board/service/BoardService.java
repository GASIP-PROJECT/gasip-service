package com.example.gasip.Board.service;

import com.example.gasip.Board.dto.BoardCreateRequest;
import com.example.gasip.Board.dto.BoardCreateResponse;
import com.example.gasip.Board.dto.BoardUpdateRequest;
import com.example.gasip.Board.dto.BoardUpdateResponse;
import com.example.gasip.Board.model.Board;
import com.example.gasip.Board.repository.BoardRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 post
    @Transactional
    public BoardCreateResponse createBoard(BoardCreateRequest boardCreateRequest) {
        Board board = boardRepository.save(boardCreateRequest.toEntity(boardCreateRequest));
        return BoardCreateResponse.fromEntity(board);
    }

    @Transactional(readOnly = true)
    public List<Board> findAllBoard() {
        return boardRepository.findAll();
    }

    @Transactional
    public BoardUpdateResponse editBoard(Long boardId, @Valid BoardUpdateRequest boardUpdateRequest) {
        return null;
    }
    @Transactional
    public String deleteBoard(Long boardId) {
        return "";
    }
}
