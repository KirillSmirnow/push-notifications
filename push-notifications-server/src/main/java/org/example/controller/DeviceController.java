package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.DeviceService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping(path = "/users/{userId}/devices/{deviceId}/tokens", consumes = MediaType.TEXT_PLAIN_VALUE)
    public void registerDeviceToken(@PathVariable int userId, @PathVariable String deviceId, @RequestBody String token) {
        deviceService.registerToken(userId, deviceId, token);
    }

    @DeleteMapping("/devices/{deviceId}/tokens")
    public void removeDeviceToken(@PathVariable String deviceId) {
        deviceService.removeToken(deviceId);
    }
}
