package com.icia.board.entity;

import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.dto.BoardUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 50)
    private String boardWriter;

    @Column(length = 20)
    private String boardPassword;

    @Column(length = 30)
    private String boardTitle;

    @Column(length = 500)
    private String boardContenst;

    //회원 엔티티와의 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;

    //댓글 연관관계
    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

//    @Column(length = 100)
//    private LocalDateTime boardDate;
    public static BoardEntity saveBoard(BoardSaveDTO boardSaveDTO,MemberEntity memberEntity){
        BoardEntity boardEntity = new BoardEntity();
//        boardEntity.setBoardWriter(boardSaveDTO.getB_writer());
        boardEntity.setBoardPassword(boardSaveDTO.getB_password());
        boardEntity.setBoardTitle(boardSaveDTO.getB_title());
        boardEntity.setBoardContenst(boardSaveDTO.getB_contents());
//        boardEntity.setBoardDate(LocalDateTime.now());

        return boardEntity;
    }

    public static BoardEntity updateBoard(BoardUpdateDTO boardUpdateDTO){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardUpdateDTO.getBoardWriter());
        boardEntity.setBoardPassword(boardUpdateDTO.getBoardPassword());
        boardEntity.setBoardTitle(boardUpdateDTO.getBoardTitle());
        boardEntity.setBoardContenst(boardUpdateDTO.getBoardContents());
//        boardEntity.setBoardDate(LocalDateTime.now());

        return boardEntity;
    }


}
