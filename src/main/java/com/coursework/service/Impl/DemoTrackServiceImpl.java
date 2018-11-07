package com.coursework.service.Impl;

import com.coursework.model.DemoTrack;
import com.coursework.model.User;
import com.coursework.repository.DemoTrackRepository;
import com.coursework.service.DemoTrackService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoTrackServiceImpl implements DemoTrackService {

    @Autowired
    DemoTrackRepository demoTrackRepository;

    public List<DemoTrack> findAll() {
        return demoTrackRepository.findAll();
    }

    public List<DemoTrack> findAllByUser(User user) {
        return demoTrackRepository.findAllByUser(user);
    }

    public void saveDemoTrack(DemoTrack demoTrack) {
        demoTrack.setChecked(false);
        demoTrackRepository.save(demoTrack);
    }

    @Override
    public void deleteDemoTrack(ObjectId id) {
        demoTrackRepository.deleteById(id.toHexString());
    }

}