package com.cpoyraz.notepad.service;

import com.cpoyraz.notepad.dto.request.note.AddNoteRequest;
import com.cpoyraz.notepad.dto.request.note.EditNoteRequest;
import com.cpoyraz.notepad.model.Note;
import com.cpoyraz.notepad.model.Tag;
import com.cpoyraz.notepad.model.User;
import com.cpoyraz.notepad.repository.NoteRepository;
import com.cpoyraz.notepad.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final TagRepository tagRepository;
    private final MongoTemplate mongoTemplate;

    public Note add(AddNoteRequest request, User authenticatedUser) {
        List<Tag> tags = new ArrayList<>();

        for (String tagName : request.getTags()) {
            Tag tag = tagRepository.findByText(tagName);

            if (tag == null) {
                tag = new Tag();
                tag.setText(tagName);
                tag.setCreatedBy(authenticatedUser);
                tag.setCount(0);
                tagRepository.save(tag);
            }

            tag.setCount(tag.getCount() + 1);
            tagRepository.save(tag);
            tags.add(tag);
        }

        Note note = new Note();
        note.setTitle(request.getTitle());
        note.setText(request.getText());
        note.setTags(tags);
        note.setCreatedBy(authenticatedUser);

        return noteRepository.save(note);
    }

    public Optional<Note> getNoteById(String noteId){
        return noteRepository.findById(noteId);
    }

    public Page<Note> getAllNotes(Pageable pageable) {
        return noteRepository.findAll(pageable);
    }

    public Boolean deleteNote(String noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("Böyle bir not bulunamadı."));

        noteRepository.delete(note);
        return true;
    }

    public Note edit(EditNoteRequest request, User authenticatedUser) {
        Note note = noteRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Böyle bir not bulunamadı."));

        if (!request.getTags().isEmpty()) {
            List<Tag> tags = new ArrayList<>();

            for (String tagName : request.getTags()) {
                Tag tag = tagRepository.findByText(tagName);
                if (tag == null) {
                    tag = new Tag();
                    tag.setText(tagName);
                    tag.setCreatedBy(authenticatedUser);
                }
                tag.setCount(tag.getCount() + 1);
                tagRepository.save(tag);
                tags.add(tag);
            }

            note.setTags(tags);
        }

        if(!request.getTitle().isEmpty())
            note.setTitle(request.getTitle());

        if(!request.getText().isEmpty())
            note.setText(request.getText());

        return noteRepository.save(note);
    }

}
