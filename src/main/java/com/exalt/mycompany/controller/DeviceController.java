package com.exalt.mycompany.controller;


import com.exalt.mycompany.dto.DeviceDTO;
import com.exalt.mycompany.dto.RoleDTO;
import com.exalt.mycompany.model.Device;
import com.exalt.mycompany.model.Role;
import com.exalt.mycompany.service.DeviceService;
import com.exalt.mycompany.service.RoleService;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/v1/")
@RestController
public class DeviceController {

    private DeviceService deviceService;

    private final static Logger logger = LoggerFactory.getLogger(DeviceController.class);

    private ModelMapper deviceMapper;

    public DeviceController() {
        deviceMapper = new ModelMapper();
        deviceMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        deviceMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    @Autowired
    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("devices")
    public List<DeviceDTO> getDevices() {
        return deviceService.getAllDevices().stream().map(device -> deviceMapper.map(device, DeviceDTO.class)).collect(Collectors.toList());

    }

    @GetMapping("devices/{id}")
    public DeviceDTO getDeviceWithID(@PathVariable(value = "id") int id) {
        return deviceMapper.map(deviceService.getDeviceWithID(id), DeviceDTO.class);
    }

    @PostMapping(value = "devices")
    public void createNewDevice(@RequestBody DeviceDTO u) {
        deviceService.createNewDevice(deviceMapper.map(u, Device.class));
    }

    @PutMapping(value = "devices/{id}")
    public void updateDevice(@PathVariable(value = "id") int id, @RequestBody DeviceDTO d) {
        deviceService.updateDevice(id, deviceMapper.map(d, Device.class));
    }

    @DeleteMapping(value = "devices/{id}")
    public void deleteDevices(@PathVariable(value = "id") int id) {
        deviceService.deleteDevice(id);
    }
}
