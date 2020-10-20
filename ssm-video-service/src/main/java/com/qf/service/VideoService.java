package com.qf.service;

import com.qf.pojo.QueryVo;
import com.qf.pojo.Video;

import java.util.List;

public interface VideoService {
    List<Video> findAll(QueryVo queryVo);

    Video findById(Integer id);

    void updateVideo(Video video);

    void insertVideo(Video video);

    void delete(Integer params);
}
