package com.cpoyraz.notepad.dto.request.note;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditNoteRequest {
    private String id;
    private String title;
    private String text;
    private List<String> tags;
}

