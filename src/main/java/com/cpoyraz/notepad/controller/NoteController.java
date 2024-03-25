package com.cpoyraz.notepad.controller;

import com.cpoyraz.notepad.dto.request.note.AddNoteRequest;
import com.cpoyraz.notepad.model.Note;
import com.cpoyraz.notepad.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @PostMapping("/add")
    public ResponseEntity<Note> add(@RequestBody AddNoteRequest request) {
        return ResponseEntity.ok(noteService.add(request));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Note>> all(@RequestParam("page") int page,
                                          @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        return ResponseEntity.ok(noteService.getAllNotes(pageable));
    }
}
