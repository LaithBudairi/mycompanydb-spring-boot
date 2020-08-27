package com.exalt.mycompany.service;

import com.exalt.mycompany.model.Device;
import com.exalt.mycompany.model.User;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author LaithB
 */
public interface DeviceService {

    /**
     * @return all devices existing in the database.
     * @throws EntityNotFoundException if no devices found
     */
    List<Device> getAllDevices();

    /**
     *
     * @param id device id to search for
     * @return device with the specified id
     * @throws EntityNotFoundException if the device with the specified id is not found
     */
    Device getDeviceWithID(int id);

    /**
     *
     * @param device device to create
     */
    void createNewDevice(Device device);

    /**
     *
     * @param id device id to update its info
     * @param d info to update device
     * @return void
     * @throws EntityNotFoundException if the user with the specified id is not found
     */
    void updateDevice(int id, Device d);
    /**
     *
     * @param id device id to delete
     * @return void
     */
    void deleteDevice(int id);
}
