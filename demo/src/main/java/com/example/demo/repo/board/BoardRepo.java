package com.example.demo.repo.board;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.board.Board;

public interface BoardRepo extends CrudRepository<Board, Long> {

}
