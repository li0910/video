package com.qf.service.impl;

import com.qf.dao.VideoMapper;
import com.qf.pojo.QueryVo;
import com.qf.pojo.Video;
import com.qf.pojo.VideoExample;
import com.qf.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;


    @Override
    public List<Video> findAll(QueryVo queryVo) {
        return videoMapper.findAll(queryVo);
    }

    @Override
    public Video findById(Integer id) {
        return videoMapper.selectByPrimaryKey(id);
    }
// 修改video方法
    @Override
    public void updateVideo(Video video) {
        VideoExample videoExample = new VideoExample();

        VideoExample.Criteria criteria = videoExample.createCriteria();

        criteria.andIdEqualTo(video.getId());
        criteria.andVideoUrlLike("%" + video.getVideoUrl() + "%");
        videoMapper.updateByExample(video,videoExample);

    }
// 添加video方法
    @Override
    public void insertVideo(Video video) {
        videoMapper.insert(video);
    }

//    删除video的方法
    @Override
    public void delete(Integer params) {
        videoMapper.deleteByPrimaryKey(params);
    }


}
