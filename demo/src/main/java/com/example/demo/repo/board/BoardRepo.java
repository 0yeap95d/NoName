package com.example.demo.repo.board;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.board.Board;

public interface BoardRepo extends MongoRepository<Board, String> {

}
