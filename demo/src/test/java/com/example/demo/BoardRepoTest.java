package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.board.Board;
import com.example.demo.repo.board.BoardRepo;

@SpringBootTest
public class BoardRepoTest {
	
	@Autowired
	private BoardRepo boardRepo;
	
	@AfterEach
	public void tearDown() throws Exception {
		boardRepo.deleteAll();
	}
	
	@Test
	public void basicSave() {
		// given
		Board board = new Board(null, "title1", "content1", "author1");
		
		// when
		Board savedBoard = boardRepo.save(board);
		
		// then
		Optional<Board> findBoard = boardRepo.findById(savedBoard.getId()); 
		
		assertThat(findBoard.isPresent()).isEqualTo(Boolean.TRUE);
		assertThat(findBoard.get().getTitle()).isEqualTo(board.getTitle());
	}
}
