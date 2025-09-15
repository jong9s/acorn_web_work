package com.example.spring08.repository;

import java.util.List;

import com.example.spring08.dto.BoardDto;

public interface BoardDao {
	
	public List<BoardDto> selectPage(BoardDto dto);
	public int getCount(BoardDto dto);

	public void insert(BoardDto dto);
	public int delete(int num);
	public int update(BoardDto dto);
	
	public BoardDto getByNum(int num);
	public BoardDto getByDto(BoardDto dto);
}
