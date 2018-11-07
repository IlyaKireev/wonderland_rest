package com.coursework.service;

import com.coursework.model.DemoTrack;
import org.bson.types.ObjectId;

import java.util.List;

public interface DemoTrackService {

    List<DemoTrack> findAll();

    void saveDemoTrack(DemoTrack demoTrack);

    void deleteDemoTrack(ObjectId id);

}