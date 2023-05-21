package com.example.communityapp.repository;

import com.example.communityapp.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Integer> {
    //jpa레포지토리에는 <객체,기본키의타입> 을 적는다
}
