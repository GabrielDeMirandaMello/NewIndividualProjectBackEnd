package com.travel.stories.travels.internal.usecase;

import com.travel.stories.travels.api.usecase.GetStoryById;
import com.travel.stories.travels.internal.entity.Story;
import com.travel.stories.travels.internal.repository.StoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Transactional
@AllArgsConstructor
public class DefaultGetStoryById implements GetStoryById {
    @Autowired
    private StoryRepository storyRepository;

    @Override
    public ResponseEntity<Story> execute(Long id) {
        Optional<Story> history = this.storyRepository.findById(id);
        return history.map( value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
