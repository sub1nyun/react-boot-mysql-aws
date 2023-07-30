package com.sbyoon.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbyoon.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

}
