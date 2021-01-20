package com.example.demo.entity.board;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@RedisHash("board")
public class Board {
	
	@Id
	private Long id;
	
	private String title;
	private String content;
	private String author;
	
	@Builder
	public Board(Long id, String title, String content, String author) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
	}
}
