package com.cpoyraz.notepad.service;

import com.cpoyraz.notepad.dto.request.note.AddNoteRequest;
import com.cpoyraz.notepad.model.Note;
import com.cpoyraz.notepad.model.Tag;
import com.cpoyraz.notepad.model.User;
import com.cpoyraz.notepad.repository.NoteRepository;
import com.cpoyraz.notepad.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Note> getNoteById(String noteId){
        return noteRepository.findById(noteId);
    }

    public Page<Note> getAllNotes(Pageable pageable) {
        return noteRepository.findAll(pageable);
    }
}
