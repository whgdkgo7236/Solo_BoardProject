package com.icia.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardSaveDTO {
    private String b_writer;
    private String b_password;
    private String b_title;
    private String b_contents;

    public static BoardSaveDTO toBoardSaveDTO(BoardSaveDTO boardSaveDTO){
        return boardSaveDTO;
    }
}
