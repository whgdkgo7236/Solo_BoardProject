package com.icia.board.entity;

import com.icia.board.dto.CommentSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name= "comment_table")
public class CommentEntity extends BaseEntity{
    //시간은 BaseEntity에서 만들어준다
    //댓글, 작성자, 내용, 원글
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    //회원 엔티티연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id") // 부모테이블(참조하고자 하는 테이블)의 pk컬럼이름
    private MemberEntity memberEntity;

    //원글의 게시글 번호를 참조하기 위한 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")//부모테이블(참조하고자하는 테이블)의 pk컬럼이름
    private BoardEntity boardEntity;//참조하고자 하는 테이블을 관리하는 앤티티
    
    @Column
    private String commentWriter;
    
    @Column 
    private String commentContents;


    public static CommentEntity toSaveEntity(CommentSaveDTO commentSaveDTO, BoardEntity boardEntity,MemberEntity member){
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(commentSaveDTO.getCommentWriter());
        commentEntity.setCommentContents(commentEntity.getCommentContents());
        commentEntity.setBoardEntity(boardEntity);
        return commentEntity;
    }
    
    
}
