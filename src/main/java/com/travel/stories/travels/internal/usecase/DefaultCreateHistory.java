package com.travel.stories.travels.internal.usecase;

import com.travel.stories.travels.api.usecase.CreateHistory;
import com.travel.stories.travels.internal.entity.History;
import com.travel.stories.travels.internal.repository.HistoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@Transactional
@AllArgsConstructor
public class DefaultCreateHistory implements CreateHistory {

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public ResponseEntity<History> execute(History history) {
        return new ResponseEntity<>(this.historyRepository.save(history), HttpStatus.CREATED);
    }
}
