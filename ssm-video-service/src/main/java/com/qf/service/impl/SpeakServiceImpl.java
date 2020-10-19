package com.qf.service.impl;

import com.qf.dao.SpeakerMapper;
import com.qf.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeakServiceImpl implements SpeakerService {
    @Autowired
    private SpeakerMapper speakerMapper;
}
