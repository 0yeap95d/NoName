package com.example.demo.dto.board;

import com.example.demo.entity.board.Board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardSaveRequestDto {
	
	private Long id;
	private String title;
	private String content;
	private String author;

	@Builder
	public BoardSaveRequestDto(Long id, String title, String content, String author) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
	}
	
	public Board toRedisHash() {
		return Board.builder()
				.id(id)
				.title(title)
				.content(content)
				.author(author)
				.build();
	}
	
}
