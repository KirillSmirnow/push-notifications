package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Device;
import org.example.repository.DeviceRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;

    @Transactional
    public void registerToken(int userId, String deviceId, String deviceToken) {
        var user = userRepository.findById(userId).orElseThrow();
        deviceRepository.findById(deviceId).ifPresentOrElse(
                device -> {
                    if (!device.getUser().equals(user)) {
                        throw new IllegalStateException("The device belongs to another user");
                    }
                    device.setToken(deviceToken);
                },
                () -> deviceRepository.save(new Device(deviceId, user, deviceToken))
        );
    }

    @Transactional
    public void removeToken(String deviceId) {
        var device = deviceRepository.findById(deviceId).orElseThrow();
        device.setToken(null);
    }
}
