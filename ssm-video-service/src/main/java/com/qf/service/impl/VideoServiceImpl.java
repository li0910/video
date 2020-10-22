package com.qf.service.impl;

import com.qf.dao.VideoMapper;
import com.qf.pojo.Video;
import com.qf.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> findAllVideo(int id) {
        return videoMapper.findAllVideo(id);
    }

    @Override
    public Video findById(Integer id) {
        return videoMapper.findById(id);
    }

    @Override
    public Video findByVideoId(Integer id) {
        return videoMapper.findByVideoId(id);
    }
}
