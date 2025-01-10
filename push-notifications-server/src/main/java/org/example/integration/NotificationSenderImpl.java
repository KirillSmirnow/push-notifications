package org.example.integration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationSenderImpl implements NotificationSender {

    private static final String CREDENTIALS_PATH = "../../creds/push-demo-key.json";

    @EventListener(ApplicationReadyEvent.class)
    @SneakyThrows
    public void initialize() {
        var options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream(CREDENTIALS_PATH)))
                .build();
        FirebaseApp.initializeApp(options);
    }

    @Override
    @SneakyThrows
    public void send(String deviceToken, String title, String text) {
        log.info("Notifying by device token='{}': title='{}' and text='{}'", deviceToken, title, text);
        try {
            FirebaseMessaging.getInstance().send(
                    Message.builder()
                            .setToken(deviceToken)
                            .setNotification(
                                    Notification.builder()
                                            .setTitle(title)
                                            .setBody(text)
                                            .build()
                            )
                            .build()
            );
        } catch (Exception e) {
            log.warn("Failed to send notification: {}", e.getMessage());
        }
    }
}
