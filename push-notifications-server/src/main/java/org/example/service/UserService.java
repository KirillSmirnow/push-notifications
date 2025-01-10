package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public int getAnyUserId() {
        var allUsers = userRepository.findAll();
        Collections.shuffle(allUsers);
        return allUsers.getFirst().getId();
    }
}
