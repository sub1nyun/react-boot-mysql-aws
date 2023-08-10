package com.sbyoon.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbyoon.board.entity.PopularSearchEntity;

public interface PopularSearchRepository extends JpaRepository<PopularSearchEntity, String> {

	public List<PopularSearchEntity> findTop10ByOrderByPopularSearchCountDesc();
	
	
	
	
	
}
