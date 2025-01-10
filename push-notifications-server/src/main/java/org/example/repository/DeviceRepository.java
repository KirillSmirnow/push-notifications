package org.example.repository;

import org.example.domain.Device;
import org.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface DeviceRepository extends JpaRepository<Device, String> {

    Set<Device> findByUser(User user);
}
