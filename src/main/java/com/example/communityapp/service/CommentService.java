package com.example.communityapp.service;

import com.example.communityapp.entity.Board;
import com.example.communityapp.entity.Comment;
import com.example.communityapp.repository.BoardRepository;
import com.example.communityapp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    CommentRepository commentRepository;


    public void saveComment(Comment comment){

        commentRepository.save(comment);
    }


}
