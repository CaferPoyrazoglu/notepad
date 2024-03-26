package com.cpoyraz.notepad.controller;

import com.cpoyraz.notepad.dto.request.note.AddTagRequest;
import com.cpoyraz.notepad.model.Tag;
import com.cpoyraz.notepad.service.AuthenticationService;
import com.cpoyraz.notepad.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    private final AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<Tag> add(@RequestBody AddTagRequest request) {
        return ResponseEntity.ok(tagService.add(request, authenticationService.getAuthenticatedUser()));
    }


    @GetMapping("/all")
    public ResponseEntity<Page<Tag>> all(@RequestParam("page") int page,
                                         @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").ascending());
        return ResponseEntity.ok(tagService.getAllTags(pageable));
    }

}
