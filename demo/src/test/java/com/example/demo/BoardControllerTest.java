package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.board.BoardSaveRequestDto;
import com.example.demo.dto.board.BoardUpdateRequestDto;
import com.example.demo.entity.board.Board;
import com.example.demo.repo.board.BoardRepo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardControllerTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private BoardRepo boardRepo;
	
	@AfterEach
	public void tearDown() throws Exception {
		boardRepo.deleteAll();
	}
	
	@Test
	public void registBoard() throws Exception {
		String title = "title";
		String content = "content";
		BoardSaveRequestDto requestDto = BoardSaveRequestDto.builder()
				.title(title)
				.content(content)
				.author("author")
				.build();
		
		String url = "http://localhost:" + port + "/board";
		
		ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
		
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//		assertThat(responseEntity.getBody()).isGreaterThan(0L);
//		List<Board> all = (List<Board>) boardRepo.findAll();
//		
//		assertThat(all.get(0).getTitle()).isEqualTo(title);
//		assertThat(all.get(0).getContent()).isEqualTo(content);
//		
	}
	
	@Test
    public void modifyPosts() throws Exception {
        Board savedPosts = boardRepo.save(Board.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        BoardUpdateRequestDto requestDto = BoardUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/board/" + updateId;

        HttpEntity<BoardUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        List<Board> all = (List<Board>) boardRepo.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
//        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }

}
