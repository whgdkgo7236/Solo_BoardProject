package com.icia.board.entity;

import com.icia.board.dto.BoardSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity {
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

    @Column(length = 100)
    private LocalDateTime boardDate;

    public static BoardEntity saveBoard(BoardSaveDTO boardSaveDTO){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardSaveDTO.getB_writer());
        boardEntity.setBoardPassword(boardSaveDTO.getB_password());
        boardEntity.setBoardTitle(boardSaveDTO.getB_title());
        boardEntity.setBoardContenst(boardSaveDTO.getB_contents());
        boardEntity.setBoardDate(LocalDateTime.now());

        return boardEntity;
    }
}
