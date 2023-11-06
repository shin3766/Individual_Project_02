package com.sparta.individual_project_02.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class BoardRequestDTO {
    private String title;
    private String username;
    private transient String password;
    private String contents;
    private LocalDate localDate = LocalDate.now();
}
