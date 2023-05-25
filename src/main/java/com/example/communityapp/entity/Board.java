package com.example.communityapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Board {
    @Id
    //@Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String title;
    private String content;


    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();
}
