package com.exalt.mycompany.dto;

import com.exalt.mycompany.model.User;

public class DeviceDTO {

    private String name;
    private UserDTO user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "DeviceDTO{" +
                "name='" + name + '\'' +
                ", user=" + user +
                '}';
    }
}
