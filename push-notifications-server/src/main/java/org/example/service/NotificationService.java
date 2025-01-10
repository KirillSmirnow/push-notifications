package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Device;
import org.example.integration.NotificationSender;
import org.example.repository.DeviceRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final NotificationSender notificationSender;

    private final AtomicInteger pushCounter = new AtomicInteger(0);

    public void notify(int userId) {
        var user = userRepository.findById(userId).orElseThrow();
        var deviceTokens = deviceRepository.findByUser(user).stream()
                .map(Device::getToken)
                .filter(Objects::nonNull)
                .collect(toSet());
        var title = "PUSH-" + pushCounter.incrementAndGet();
        deviceTokens.forEach(deviceToken ->
                notificationSender.send(deviceToken, title, LocalDateTime.now().toString())
        );
    }
}
