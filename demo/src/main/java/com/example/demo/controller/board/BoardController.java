package com.example.demo.controller.board;

import java.util.Arrays;
import java.util.Set;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.board.BoardResponseDto;
import com.example.demo.dto.board.BoardSaveRequestDto;
import com.example.demo.dto.board.BoardUpdateRequestDto;
import com.example.demo.service.board.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardController {
	
	private final BoardService boardService;
	private final StringRedisTemplate redisTemplate;
	
//	@GetMapping("/board")
//	public String ok() {
//		return "ok";
//	}
//	
	@GetMapping("board/keys")
	public String keys() {
		Set<String> keys = redisTemplate.opsForSet().members("*");
		assert keys != null;
		return Arrays.toString(keys.toArray());
	}
	
	@PostMapping("/board")
	public String save(@RequestBody BoardSaveRequestDto requestDto) {
		return boardService.save(requestDto);
	}
	
	@PutMapping("/board/{id}")
	public String update(@PathVariable String id, @RequestBody BoardUpdateRequestDto requestDto) {
		return boardService.update(id, requestDto);
	}
	
	@GetMapping("/board/{id}")
	public BoardResponseDto findById(@PathVariable String id) {
		return boardService.findById(id);
	}
	
	@DeleteMapping("/board/{id}")
	public String delete(@PathVariable String id) {
		return boardService.delete(id);
	}
}
