package com.qf.service;

import com.qf.pojo.Video;

import java.util.List;

import com.qf.pojo.QueryVo;

public interface VideoService {
	List<Video> findAllVideo(int id);

	Video findById(Integer id);

	Video findByVideoId(Integer id);
    List<Video> findAll(QueryVo queryVo);

    void updateVideo(Video video);

    void insertVideo(Video video);

    void delete(Integer params);
}
