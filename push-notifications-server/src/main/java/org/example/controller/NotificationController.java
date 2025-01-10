package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.NotificationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/users/{id}/notify")
    public void notifyUser(@PathVariable int id) {
        notificationService.notify(id);
    }
}
