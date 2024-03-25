package com.cpoyraz.notepad.repository;

import com.cpoyraz.notepad.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

}
