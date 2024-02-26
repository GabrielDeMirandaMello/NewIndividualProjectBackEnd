package com.travel.stories.travels.internal.usecase;

import com.travel.stories.travels.api.usecase.UpdateLike;
import com.travel.stories.travels.internal.entity.History;
import com.travel.stories.travels.internal.repository.HistoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@NoArgsConstructor
@AllArgsConstructor
public class DefaultUpdateLike implements UpdateLike {

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public void execute(Long id) {
        History history = this.historyRepository.findById(id).orElseThrow();
        history.setLikeCount(history.getLikeCount() + 1);
        this.historyRepository.save(history);
    }
}
