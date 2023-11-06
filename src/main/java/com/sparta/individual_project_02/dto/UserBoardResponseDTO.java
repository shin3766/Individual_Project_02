package com.sparta.individual_project_02.dto;

import com.sparta.individual_project_02.entity.Board;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class UserBoardResponseDTO {
    private String title;
    private String username;


    public UserBoardResponseDTO(Board board) {
        this.title = board.getTitle();
        this.username = board.getUsername();
    }
}
