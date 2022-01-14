package com.icia.board.dto;

import com.icia.board.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDetailDTO {
    private Long boardId;
    private String boardWriter;
    private String boardPassword;
    private String boardTitle;
    private String boardContents;
    private LocalDateTime boardDate;

    public static BoardDetailDTO toBoardEntity(BoardEntity boardEntity){
        BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
        boardDetailDTO.setBoardId(boardEntity.getId());
        boardDetailDTO.setBoardDate(boardEntity.getCreateTime());
        boardDetailDTO.setBoardContents(boardEntity.getBoardContenst());
        boardDetailDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDetailDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDetailDTO.setBoardPassword(boardEntity.getBoardPassword());

        return boardDetailDTO;
    }
}
