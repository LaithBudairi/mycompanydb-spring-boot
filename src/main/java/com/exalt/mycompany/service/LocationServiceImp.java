package com.exalt.mycompany.service;

import com.exalt.mycompany.model.Location;
import com.exalt.mycompany.model.Role;
import com.exalt.mycompany.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class LocationServiceImp implements LocationService {

    private LocationRepository locationRepository;

    @Autowired
    public void setLocationRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> getAllLocations() {
        List<Location> locations =  (List<Location>) locationRepository.findAll();
        if(locations.size() == 0) {
            throw new EntityNotFoundException("No Roles Found");

        }
        return locations;
    }

    @Override
    public Location getLocationWithID(int id) {
        return locationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Location With ID={" + id + "} Was Found"));
    }

    @Override
    public void createNewLocation(Location location) {
        locationRepository.save(location);
    }

    @Override
    @Transactional
    public void updateLocation(int id, Location l) {
        getLocationWithID(id);
        l.setId(id);
        locationRepository.save(l);
    }

    @Override
    public void deleteLocation(int id) {
        locationRepository.deleteById(id);
    }
}
