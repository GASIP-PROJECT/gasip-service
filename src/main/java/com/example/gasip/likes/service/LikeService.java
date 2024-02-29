package com.example.gasip.likes.service;

import com.example.gasip.profboard.model.ProfBoard;
import com.example.gasip.profboard.repository.ProfBoardRepository;
import com.example.gasip.exception.DuplicateResourceException;
import com.example.gasip.likes.dto.LikeRequestDto;
import com.example.gasip.likes.model.Likes;
import com.example.gasip.likes.repository.LikeRepository;
import com.example.gasip.member.model.Member;
import com.example.gasip.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final ProfBoardRepository boardRepository;

    @Transactional
    public void insert(LikeRequestDto likeRequestDto) throws Exception {

        Member member = memberRepository.findById(likeRequestDto.getMemberId())
                .orElseThrow(() -> new NotFoundException("Could not found member id : " + likeRequestDto.getMemberId()));

        ProfBoard profBoard = boardRepository.findById(likeRequestDto.getPostId())
                .orElseThrow(() -> new NotFoundException("Could not found board id : " + likeRequestDto.getPostId()));

        // 이미 좋아요되어있으면 에러 반환
        if (likeRepository.findByMemberAndBoard(member, profBoard).isPresent()){
            //TODO 409에러로 변경
            throw new DuplicateResourceException("already exist data by member id :" + member.getMemberId() + " ,"
                    + "board id : " + profBoard.getPostId());
        }

        Likes likes = Likes.builder()
                .board(profBoard)
                .member(member)
                .build();

        likeRepository.save(likes);
        boardRepository.addLikeCount(profBoard);
    }

    @Transactional
    public void delete(LikeRequestDto likeRequestDto) {

        Member member = memberRepository.findById(likeRequestDto.getMemberId())
                .orElseThrow(() -> new NotFoundException("Could not found member id : " + likeRequestDto.getMemberId()));

        ProfBoard profBoard = boardRepository.findById(likeRequestDto.getPostId())
                .orElseThrow(() -> new NotFoundException("Could not found board id : " + likeRequestDto.getPostId()));

        Likes likes = likeRepository.findByMemberAndBoard(member, profBoard)
                .orElseThrow(() -> new NotFoundException("Could not found heart id"));

        likeRepository.delete(likes);
        boardRepository.subLikeCount(profBoard);
    }
}
