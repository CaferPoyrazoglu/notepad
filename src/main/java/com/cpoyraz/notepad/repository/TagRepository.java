package com.cpoyraz.notepad.repository;

import com.cpoyraz.notepad.model.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends MongoRepository<Tag, String> {

}
