package com.example.demo.dto.board;

import com.example.demo.entity.board.Board;

import lombok.Getter;

@Getter
public class BoardResponseDto {
	
	private String id;
	private String title;
	private String content;
	private String author;
	
	public BoardResponseDto(Board entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.author = entity.getAuthor();
	}
}
