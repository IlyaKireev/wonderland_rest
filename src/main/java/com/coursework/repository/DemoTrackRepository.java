package com.coursework.repository;

import com.coursework.model.DemoTrack;
import com.coursework.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DemoTrackRepository extends MongoRepository<DemoTrack, String> {

    List<DemoTrack>  findAllByUser(User user);

}
