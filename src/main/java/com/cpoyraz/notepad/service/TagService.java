package com.cpoyraz.notepad.service;

import com.cpoyraz.notepad.dto.request.note.AddTagRequest;
import com.cpoyraz.notepad.model.Tag;
import com.cpoyraz.notepad.model.User;
import com.cpoyraz.notepad.repository.TagRepository;
import com.cpoyraz.notepad.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    public Tag add(AddTagRequest request, User authenticatedUser) {
        Tag tag = new Tag();
        tag.setText(request.getText());
        tag.setCreatedBy(authenticatedUser);
        return tagRepository.save(tag);
    }

    public Page<Tag> getAllTags(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    public Boolean deleteTag(String tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new IllegalArgumentException("Böyle bir etiket bulunamadı."));

        tagRepository.delete(tag);
        return true;
    }
}
