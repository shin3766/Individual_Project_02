package com.sparta.individual_project_02.entity;

import com.sparta.individual_project_02.dto.BoardRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class Board {
    private String title;
    private String username;
    private transient String password;
    private String contents;
    private LocalDate localDate = LocalDate.now();

    public Board(BoardRequestDTO requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
        this.localDate = requestDto.getLocalDate();
    }

    public void update(BoardRequestDTO requestDTO) {
        this.title = requestDTO.getTitle();
        this.username = requestDTO.getUsername();
        this.password = requestDTO.getPassword();
        this.contents = requestDTO.getContents();
    }
}
