package com.example.communityapp.controller;

import com.example.communityapp.entity.Board;
import com.example.communityapp.entity.Comment;
import com.example.communityapp.repository.BoardRepository;
import com.example.communityapp.repository.CommentRepository;
import com.example.communityapp.service.BoardService;
import com.example.communityapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    BoardRepository boardRepository;






    @PostMapping("/board/updateComment/{id}")
    public String createComment(@PathVariable("id") Integer id,Model model,Comment comment){


        Optional<Board> board=boardRepository.findById(comment.getId());
        System.out.println(board.get());
        comment.setBoard(board.get());
        System.out.println(comment);
        commentService.saveComment(comment);

        model.addAttribute("board",board);
        model.addAttribute("message","댓글을 달았습니다.. 욕은 적지마시고요");
        model.addAttribute("searchUrl","/board/view/"+id);

        return "message";
    }





//    @GetMapping("/board/view")
//    public String boardWithCommentView(Model model, Integer id){
//
//        return "boardview";
//    }



//    @PostMapping("/board/writeprocess")
//    public String boardWriteProcess(Board board,Model model){
//
//        boardService.write(board);
//
//        model.addAttribute("message","글이 작성되었습니다.");
//        model.addAttribute("searchUrl","/board/list");
//
//        return "message";
//    }
//
//    @GetMapping("/board/view")//localhost:8080/board/view?id=1 > pk가 1인 값
//    public String boardView(Model model, Integer id){
//        model.addAttribute("board",boardService.view(id));
//        return "boardview";
//    }



}
