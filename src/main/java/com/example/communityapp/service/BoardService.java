package com.example.communityapp.service;

import com.example.communityapp.entity.Board;
import com.example.communityapp.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired //스프링에서 자동으로 객체 주입해주는것
    private BoardRepository boardRepository;

    //글작성 처리
    public void write(Board board){
        boardRepository.save(board);
    }

    //게시글 리스트 처리
    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
        //Pageable 쓸때 스프링프레임워크의 Pageable을 임포트해야함.
    }


    //특정게시글 처리
    public Board view(Integer id){
        return boardRepository.findById(id).get();
    }


    //특정게시글 삭제
    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }

    public void update(Board board){
        boardRepository.save(board);
    }

}
