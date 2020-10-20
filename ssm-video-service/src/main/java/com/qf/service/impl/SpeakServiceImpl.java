package com.qf.service.impl;

import com.qf.dao.SpeakerMapper;
import com.qf.pojo.Speaker;
import com.qf.pojo.SpeakerExample;
import com.qf.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakServiceImpl implements SpeakerService {
    @Autowired
    private SpeakerMapper speakerMapper;

    @Override
    public List<Speaker> findAll(Object o) {
        return speakerMapper.selectByExample(null);
    }

    @Override
    public Speaker findById(Integer id) {
        return speakerMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateSpeaker(Speaker speaker) {
        SpeakerExample example = new SpeakerExample();
        SpeakerExample.Criteria criteria = example.createCriteria();

        criteria.andIdEqualTo(speaker.getId());
        criteria.andSpeakerNameLike("%" + speaker.getSpeakerName() + "%");

        speakerMapper.updateByExample(speaker,example);
    }

    @Override
    public void insertSpeaker(Speaker speaker) {
        speakerMapper.insert(speaker);
    }

    @Override
    public void deleteSpeaker(Integer id) {
        speakerMapper.deleteByPrimaryKey(id);
    }
}
