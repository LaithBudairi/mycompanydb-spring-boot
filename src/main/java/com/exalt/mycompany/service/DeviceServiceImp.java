package com.exalt.mycompany.service;

import com.exalt.mycompany.model.Device;
import com.exalt.mycompany.model.User;
import com.exalt.mycompany.repository.DeviceRepository;
import com.exalt.mycompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DeviceServiceImp implements DeviceService{

    private DeviceRepository deviceRepository;

    @Autowired
    public void setDeviceRepository(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<Device> getAllDevices() {
        List<Device> devices =  (List<Device>) deviceRepository.findAll();
        if(devices.size() == 0) {
            throw new EntityNotFoundException("No Devices Found");

        }
        return devices;
    }

    @Override
    public Device getDeviceWithID(int id) {
        return deviceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Device With ID={" + id + "} Was Found"));

    }

    @Override
    public void createNewDevice(Device device) {
        deviceRepository.save(device);

    }

    @Override
    @Transactional
    public void updateDevice(int id, Device d) {
        getDeviceWithID(id);
        d.setId(id);
        deviceRepository.save(d);
    }

    @Override
    public void deleteDevice(int id) {
        deviceRepository.deleteById(id);
    }
}
