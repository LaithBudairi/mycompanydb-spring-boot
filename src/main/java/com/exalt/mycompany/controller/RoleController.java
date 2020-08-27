package com.exalt.mycompany.controller;

import com.exalt.mycompany.dto.RoleDTO;
import com.exalt.mycompany.dto.UserDTO;
import com.exalt.mycompany.model.Role;
import com.exalt.mycompany.model.User;
import com.exalt.mycompany.service.RoleService;
import com.exalt.mycompany.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(value = "api/v1/")
@RestController
public class RoleController {

    private RoleService roleService;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    private ModelMapper roleMapper;

    public RoleController() {
        roleMapper = new ModelMapper();
        roleMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        roleMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("roles")
    public List<RoleDTO> getRoles() {
        return roleService.getAllRoles().stream().map(role -> roleMapper.map(role, RoleDTO.class)).collect(Collectors.toList());

    }

    @GetMapping("roles/{id}")
    public RoleDTO getRoleWithID(@PathVariable(value = "id") int id) {
        return roleMapper.map(roleService.getRoleWithID(id), RoleDTO.class);
    }

    @PostMapping(value = "roles")
    public void createNewRole(@RequestBody RoleDTO u) {
        roleService.createNewRole(roleMapper.map(u, Role.class));
    }

    @PutMapping(value = "roles/{id}")
    public void updateRole(@PathVariable(value = "id") int id, @RequestBody RoleDTO r) {
        roleService.updateRole(id, roleMapper.map(r, Role.class));
    }

    @DeleteMapping(value = "roles/{id}")
    public void deleteRole(@PathVariable(value = "id") int id) {
        roleService.deleteRole(id);
    }
}
