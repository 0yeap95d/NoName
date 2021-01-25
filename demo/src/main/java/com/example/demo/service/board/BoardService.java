package com.example.demo.service.board;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.board.BoardResponseDto;
import com.example.demo.dto.board.BoardSaveRequestDto;
import com.example.demo.dto.board.BoardUpdateRequestDto;
import com.example.demo.entity.board.Board;
import com.example.demo.repo.board.BoardRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepo boardRepo;
	
	@Transactional
	public Long save(BoardSaveRequestDto requestDto) {
		return boardRepo.save(requestDto.toRedisHash()).getId();
	}
	
	@Transactional
	public Long update(Long id, BoardUpdateRequestDto requestDto) {
		Board board = boardRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
		board.update(requestDto.getTitle(), requestDto.getContent());
		return id;
	}
	
	public BoardResponseDto findById(Long id) {
		Board board = boardRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
		return new BoardResponseDto(board);
	}
}
