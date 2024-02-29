package com.example.gasip.profboard.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<ProfBoard> {

    private static final long serialVersionUID = -509021414L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final com.example.gasip.global.entity.QBaseTimeEntity _super = new com.example.gasip.global.entity.QBaseTimeEntity(this);

    public final NumberPath<Long> clickCount = createNumber("clickCount", Long.class);

    public final ListPath<com.example.gasip.comment.model.Comment, com.example.gasip.comment.model.QComment> comments = this.<com.example.gasip.comment.model.Comment, com.example.gasip.comment.model.QComment>createList("comments", com.example.gasip.comment.model.Comment.class, com.example.gasip.comment.model.QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final NumberPath<Long> likeCount = createNumber("likeCount", Long.class);

    public final com.example.gasip.member.model.QMember member;

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final com.example.gasip.professor.model.QProfessor professor;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QBoard(String variable) {
        this(ProfBoard.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends ProfBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(ProfBoard.class, metadata, inits);
    }

    public QBoard(Class<? extends ProfBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.gasip.member.model.QMember(forProperty("member")) : null;
        this.professor = inits.isInitialized("professor") ? new com.example.gasip.professor.model.QProfessor(forProperty("professor"), inits.get("professor")) : null;
    }

}

