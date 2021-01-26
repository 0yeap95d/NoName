package com.example.demo.entity.board;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
//@RedisHash("board")
@Document("board")
public class Board {
	
	@Id
	private String id;
	
	private String title;
	private String content;
	private String author;
	
	@Builder
	public Board(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}
	
	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
