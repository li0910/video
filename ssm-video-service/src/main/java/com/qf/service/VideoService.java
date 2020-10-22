package com.qf.service;

import com.qf.pojo.Video;

import java.util.List;

public interface VideoService {
	List<Video> findAllVideo(int id);

	Video findById(Integer id);

	Video findByVideoId(Integer id);
}
