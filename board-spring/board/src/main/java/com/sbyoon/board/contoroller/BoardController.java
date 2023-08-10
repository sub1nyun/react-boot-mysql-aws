package com.sbyoon.board.contoroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbyoon.board.dto.ResponseDto;
import com.sbyoon.board.entity.BoardEntity;
import com.sbyoon.board.entity.PopularSearchEntity;
import com.sbyoon.board.security.BoardService;

@RestController
@RequestMapping(value = "/api/board")
public class BoardController {
	
	@Autowired private BoardService boardService;
	
	@GetMapping(value = "/top3")
	public ResponseDto<List<BoardEntity>> getTop3() {
		return boardService.getTop3();
	}
	
	@GetMapping(value = "/list")
	public ResponseDto<List<BoardEntity>> getList() {
		return boardService.getList();
	}
	
	@GetMapping(value = "/popularsearchList")
	public ResponseDto<List<PopularSearchEntity>> getPopularsearchList() {
		return boardService.getPopularsearchList();
	}
	

}
