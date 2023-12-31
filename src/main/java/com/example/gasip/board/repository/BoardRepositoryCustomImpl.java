package com.example.gasip.board.repository;

import com.example.gasip.board.dto.BoardContentDto;
import com.example.gasip.board.dto.BoardReadResponse;
import com.example.gasip.board.dto.QBoardReadResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.gasip.board.model.QBoard.board;
import static com.example.gasip.member.model.QMember.member;

@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    @Override
    public List<BoardContentDto> findContentsByMemberId(Long id) {

        return queryFactory
            .select(Projections.constructor(BoardContentDto.class,board.postId, board.content))
            .from(board)
            .leftJoin(board.member, member)
            .where(idEqual(id))
            .orderBy(board.postId.desc())
            .fetch();
    }

    @Override
    public List<BoardReadResponse> findAllBoard() {
        return queryFactory
            .select(new QBoardReadResponse(
                board.regDate,board.updateDate,board.postId,board.content,board.clickCount,board.likeCount,board.professor.profId))
            .from(board)
            .fetch();
    }

    private BooleanExpression idEqual(Long id) {
        return (id == null) ? null : member.memberId.eq(id);
    }
}
