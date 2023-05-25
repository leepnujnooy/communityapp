package com.example.communityapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "board")
public class Comment {
    @Id
    //@Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String commentWriter;
    @Column
    private String commentContent;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
