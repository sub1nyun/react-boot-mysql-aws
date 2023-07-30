package com.sbyoon.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbyoon.board.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

}
