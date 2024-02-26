package com.travel.stories.travels.internal.usecase;

import com.travel.stories.travels.api.usecase.GetUsers;
import com.travel.stories.travels.internal.entity.User;
import com.travel.stories.travels.internal.records.UserResponse;
import com.travel.stories.travels.internal.repository.HistoryRepository;
import com.travel.stories.travels.internal.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class DefaultGetAllUsers implements GetUsers {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public ResponseEntity<List<UserResponse>> execute() {
        List<User> users = this.userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        users.stream().map(user -> {
                    user.setHistories(this.historyRepository.findAllByUsersId(user).orElseThrow());
            return userResponses.add(UserResponse.parserUser(user));
        }).collect(Collectors.toList());
        return ResponseEntity.ok(userResponses);
    }
}
