package com.example.spring08.service;

import java.util.List;

import com.example.spring08.dto.GalleryDto;
import com.example.spring08.dto.GalleryUploadRequest;
import com.example.spring08.dto.GalleryViewResponse;

public interface GalleryService {
	public List<GalleryDto> getGalleryList(); // 이미지 목록도 포함하여 gallery 목록 반환
	public void createGallery(GalleryUploadRequest galleryRequest); // 게시글 저장
	
	public GalleryViewResponse getGallery(int num);
}
