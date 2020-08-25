package com.exalt.mycompany.repository;

import com.exalt.mycompany.model.Device;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<Device, Integer> {
}
