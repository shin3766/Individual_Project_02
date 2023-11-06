package com.sparta.individual_project_02.controller;

import com.sparta.individual_project_02.dto.BoardRequestDTO;
import com.sparta.individual_project_02.dto.BoardResponseDTO;
import com.sparta.individual_project_02.dto.UserBoardResponseDTO;
import com.sparta.individual_project_02.entity.Board;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BoardController {

    private final Map<String, Board> boardList = new HashMap<>();

    //게시글 작성하기
    //제목, 작성자명, 비밀번호, 작성내용, 작성일 저장
    //저장된 게시글의 정보를 반환 받아 확인할 수 있어야함
    //비밀번호는 제외
    @PostMapping("/boards")
    public BoardResponseDTO createBoard(@RequestBody BoardRequestDTO requestDto) {
        // RequestDto -> Entity
        Board board = new Board(requestDto);
        // db에 저장
        boardList.put(board.getTitle(), board);

        // Entity -> ResponseDto
        BoardResponseDTO boardResponseDto = new BoardResponseDTO(board);

        return boardResponseDto;
    }

    //게시글 목록 조회
    //게시글 전체 조회
    //게시글 정보에 비밀번호 제외
    //게시글목록은 작성일기준 내림차순
    @GetMapping("/boards")
    public List<UserBoardResponseDTO> getBoards() {
        // Map To List
        List<UserBoardResponseDTO> responseList = boardList.values().stream()
                .map(UserBoardResponseDTO::new).toList();

        //날짜 기준 내림차순 정렬
        responseList.stream()
                .sorted(Comparator.comparing(UserBoardResponseDTO::getLocalDate).reversed())
                .collect(Collectors.toList());

        return responseList;
    }

    //선택한 게시글 조회
    //선택한 게시글 정보에 비밀번호 제외
    @GetMapping("/boards/{title}")
    public Board userBoard(@PathVariable String title) {
        if (boardList.containsKey(title)) {
            Board board = boardList.get(title);

            return board;
        } else {
            throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
        }

    }

    //선택한 게시글 수정
    //제목, 작성자명, 작성내용 수정가능
    //수정 요청때 비밀번호 함께 전달
    //비밀번호가 일치할 경우에만 수정가능
    //게시글 정보에 비밀번호 제외
    @PutMapping("/boards/{title}")
    public String updateBoard(@PathVariable String title, @RequestBody BoardRequestDTO requestDTO) {
        if (boardList.containsKey(title)){
            //해당 게시글 가져오기
            Board board = boardList.get(title);

            //비밀번호가 틀릴경우
            if(!Objects.equals(board.getPassword(), requestDTO.getPassword())){
                throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
            }

            //게시글 수정
            board.update(requestDTO);
            return board.getTitle();
        } else {
            throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
        }
    }

    //선택한 게시글 삭제
    //삭제 요청때 비밀번호 함께 전달
    //비밀번호가 일치할 경우에만 수정가능
    @DeleteMapping("boards/{title}")
    public String deleteBoard(@PathVariable String title, @RequestBody BoardRequestDTO requestDTO){
        if (boardList.containsKey(title)) {

            Board board = boardList.get(title);

            //비밀번호가 틀릴경우
            if(!Objects.equals(board.getPassword(), requestDTO.getPassword())){
                throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
            }
            //해당 게시글 삭제
            boardList.remove(title);
            return title;
        } else {
            throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
        }
    }
}
