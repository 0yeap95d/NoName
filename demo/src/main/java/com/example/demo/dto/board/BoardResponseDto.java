package com.example.demo.dto.board;

import com.example.demo.entity.board.Board;

import lombok.Getter;

@Getter
public class BoardResponseDto {
	
	private Long id;
	private String title;
	private String content;
	private String author;
	
	public BoardResponseDto(Board redisHash) {
		this.id = redisHash.getId();
		this.title = redisHash.getTitle();
		this.content = redisHash.getContent();
		this.author = redisHash.getAuthor();
	}
}
