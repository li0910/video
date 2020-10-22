package com.qf.service;

import com.qf.pojo.Speaker;

import java.util.List;

public interface SpeakerService {
    List<Speaker> findAll(Object o);

    Speaker findById(Integer id);

    void updateSpeaker(Speaker speaker);

    void insertSpeaker(Speaker speaker);

    void deleteSpeaker(Integer id);
}
