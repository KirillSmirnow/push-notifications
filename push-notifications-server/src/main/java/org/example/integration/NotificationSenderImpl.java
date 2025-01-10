package org.example.integration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationSenderImpl implements NotificationSender {

    @Override
    public void send(String deviceToken, String title, String text) {
        log.info("Notifying by device token='{}': title='{}' and text='{}'", deviceToken, title, text);
    }
}
