package com.cpoyraz.notepad.service;

import com.cpoyraz.notepad.dto.request.note.AddNoteRequest;
import com.cpoyraz.notepad.model.Note;
import com.cpoyraz.notepad.model.Tag;
import com.cpoyraz.notepad.model.User;
import com.cpoyraz.notepad.repository.NoteRepository;
import com.cpoyraz.notepad.repository.TagRepository;
import com.cpoyraz.notepad.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final TagRepository tagRepository;
    private final MongoTemplate mongoTemplate;

    public Note add(AddNoteRequest request, User authenticatedUser) {
        Query query = new Query();
        query.addCriteria(Criteria.where("text").in(request.getTags()));
        List<Tag> tagList = mongoTemplate.find(query, Tag.class);
        Note note = new Note();
        note.setTitle(request.getTitle());
        note.setText(request.getText());
        note.setTags(tagList);
        note.setCreatedBy(authenticatedUser);
        return noteRepository.save(note);
    }

    public Page<Note> getAllNotes(Pageable pageable) {
        return noteRepository.findAll(pageable);
    }
}
