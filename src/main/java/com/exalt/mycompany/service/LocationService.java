package com.exalt.mycompany.service;

import com.exalt.mycompany.model.Device;
import com.exalt.mycompany.model.Location;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author LaithB
 */
public interface LocationService {

    /**
     * @return all locations existing in the database.
     * @throws EntityNotFoundException if no devices found
     */
    List<Location> getAllLocations();

    /**
     *
     * @param id location id to search for
     * @return location with the specified id
     * @throws EntityNotFoundException if the location with the specified id is not found
     */
    Location getLocationWithID(int id);

    /**
     *
     * @param location location to create
     */
    void createNewLocation(Location location);

    /**
     *
     * @param id location id to update its info
     * @param l info to update device
     * @return void
     * @throws EntityNotFoundException if the location with the specified id is not found
     */
    void updateLocation(int id, Location l);
    /**
     *
     * @param id location id to delete
     * @return void
     */
    void deleteLocation(int id);


}
