package com.sparta.individual_project_02.dto;

import com.sparta.individual_project_02.entity.Board;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class BoardResponseDTO {
    private String title;
    private String username;
    private String contents;
    private LocalDate localDate = LocalDate.now();



    public BoardResponseDTO(Board board) {
        this.title = board.getTitle();
        this.username = board.getUsername();
        this.contents = board.getContents();
        this.localDate = board.getLocalDate();
    }
}
