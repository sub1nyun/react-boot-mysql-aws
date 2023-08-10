package com.sbyoon.board.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbyoon.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
	// select * from board where board_write_date >= ? order by board_likes_cnt desc limit 3
	public List<BoardEntity> findTop3ByBoardWriteDateAfterOrderByBoardLikesCountDesc(java.util.Date date);
	// select * from board order by board_write_date desc
	public List<BoardEntity> findByOrderByBoardWriteDateDesc();
	
	
	
}
