package com.sbyoon.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbyoon.board.entity.PopularSearchEntity;

public interface PopularSearchRepository extends JpaRepository<PopularSearchEntity, String> {

}
