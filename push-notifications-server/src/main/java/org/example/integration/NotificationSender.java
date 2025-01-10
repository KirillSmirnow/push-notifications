package org.example.integration;

public interface NotificationSender {

    void send(String deviceToken, String title, String text);
}
