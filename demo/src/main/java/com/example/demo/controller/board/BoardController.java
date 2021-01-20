package com.example.demo.controller.board;

import java.util.Arrays;
import java.util.Set;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.board.BoardResponseDto;
import com.example.demo.dto.board.BoardSaveRequestDto;
import com.example.demo.service.board.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardController {
	
	private final BoardService boardService;
	private final StringRedisTemplate redisTemplate;
	
	@GetMapping("/board")
	public String ok() {
		return "ok";
	}
	
	@GetMapping("board/keys")
	public String keys() {
		Set<String> keys = redisTemplate.opsForSet().members("*");
		assert keys != null;
		return Arrays.toString(keys.toArray());
	}
	
	@PostMapping("board/save")
	public Long save(@RequestBody BoardSaveRequestDto requestDto) {
		log.info(">>>>>>>>>>>>>>> [save] board={}", requestDto);
		return boardService.save(requestDto);
	}
	
	@GetMapping("board/get/{id}")
	public BoardResponseDto get(@PathVariable Long id) {
		return boardService.get(id);
	}
}
