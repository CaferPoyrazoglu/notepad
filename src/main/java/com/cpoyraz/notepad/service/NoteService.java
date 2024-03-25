package com.cpoyraz.notepad.service;

import com.cpoyraz.notepad.dto.request.note.AddNoteRequest;
import com.cpoyraz.notepad.model.Note;
import com.cpoyraz.notepad.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public Note add(AddNoteRequest request) {
        Note note = new Note();
        note.setText(request.getNote());
        note.setTitle(request.getTitle());
        return noteRepository.save(note);
    }

    public Page<Note> getAllNotes(Pageable pageable) {
        return noteRepository.findAll(pageable);
    }
}
