package com.example.communityapp.controller;

import com.example.communityapp.entity.Board;
import com.example.communityapp.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String boardWriteProcess(Board board,Model model){

        boardService.write(board);

        model.addAttribute("message","글이 작성되었습니다.");
        model.addAttribute("searchUrl","/board/list");

        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model, @PageableDefault(page=0,size=10,sort="id",direction = Sort.Direction.DESC) Pageable pageable){

        Page<Board> list = boardService.boardList(pageable);

        int nowPage = list.getPageable().getPageNumber() + 1; //pageable에서는 페이지가 0에서 시작. 하지만 페이지에서 1부터 시작해야해서 1추가
        int startPage = Math.max(nowPage - 4 ,1);
        int endPage = Math.min(nowPage + 9,list.getTotalPages());


        //타임리프에서 읽을수 있도록 어트리뷰트해줌
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("list",list); //boardService 클래스 안에있는 boardList 메서드 호출
        //boardList는 db에 있는 모든 튜플들을 찾아서 리턴해주는 역할을 함
        return "boardlist";
        //Pageable 어노테이션, 사이즈는 한페이지에 몇개 , 소트 뭘로 정렬할거냐 , 디렉션 오름 내림?
    }


    @GetMapping("/board/delete")
    public String boardDelete(Integer id){
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

//    @GetMapping("/board/view")//localhost:8080/board/view?id=1 > pk가 1인 값
//    public String boardView2(Model model, Integer id){
//        model.addAttribute("board",boardService.view(id));
//        return "boardview";
//    }

    @GetMapping("/board/view/{id}")
    public String boardView(@PathVariable("id") Integer id,Model model){
        model.addAttribute("board",boardService.view(id));
        return "boardview";
    }


    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id,Model model){
        model.addAttribute("board",boardService.view(id));
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id,Board board,Model model){
        Board tempBoard = boardService.view(id); //수정하기 이전의 보드 객체를 생성함
        tempBoard.setTitle(board.getTitle());
        tempBoard.setContent(board.getContent()); //수정된 내용의 보드를 덮어씌
        boardService.write(tempBoard);
        model.addAttribute("message","글이 수정되었습니다");
        model.addAttribute("searchUrl","/board/view/"+id);

        return "message";
    }




}
