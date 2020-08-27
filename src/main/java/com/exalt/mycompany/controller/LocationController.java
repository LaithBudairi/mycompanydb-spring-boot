package com.exalt.mycompany.controller;
import com.exalt.mycompany.dto.LocationDTO;
import com.exalt.mycompany.model.Location;
import com.exalt.mycompany.service.LocationService;
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
public class LocationController {

    private LocationService locationService;

    private final static Logger logger = LoggerFactory.getLogger(DeviceController.class);

    private ModelMapper locationMapper;

    public LocationController() {
        locationMapper = new ModelMapper();
        locationMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        locationMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("locations")
    public List<LocationDTO> getDevices() {
        return locationService.getAllLocations().stream().map(location -> locationMapper.map(location, LocationDTO.class)).collect(Collectors.toList());

    }

    @GetMapping("locations/{id}")
    public LocationDTO getLocationWithID(@PathVariable(value = "id") int id) {
        return locationMapper.map(locationService.getLocationWithID(id), LocationDTO.class);
    }

    @PostMapping("locations")
    public void createNewLocation(@RequestBody LocationDTO l) {
        locationService.createNewLocation(locationMapper.map(l, Location.class));
    }

    @PutMapping(value = "locations/{id}")
    public void updateLocation(@PathVariable(value = "id") int id, @RequestBody LocationDTO l) {
        locationService.updateLocation(id, locationMapper.map(l, Location.class));
    }

    @DeleteMapping(value = "locations/{id}")
    public void deleteLocation(@PathVariable(value = "id") int id) {
        locationService.deleteLocation(id);
    }

}
