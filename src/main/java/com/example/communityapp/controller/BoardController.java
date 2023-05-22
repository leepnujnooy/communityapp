package com.example.communityapp.controller;

import com.example.communityapp.entity.Board;
import com.example.communityapp.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;
    @GetMapping("/board/write") //localhost:8080/Board/write
    public String boardWriteForm(){
        return "boardwrite";
    }

    @PostMapping("/board/writeprocess")
    public String boardWriteProcess(Board board){

        boardService.write(board);

        return "";
    }

    @GetMapping("/board/list")
    public String boardList(Model model){
        model.addAttribute("list",boardService.boardList()); //boardService 클래스 안에있는 boardList 메서드 호출
        //boardList는 db에 있는 모든 튜플들을 찾아서 리턴해주는 역할을 함
        return "boardlist";
    }

    @GetMapping("/board/view")//localhost:8080/board/view?id=1 > pk가 1인 값
    public String boardView(Model model, Integer id){
        model.addAttribute("board",boardService.view(id));
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id){
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }


}
