package com.exalt.mycompany.service;

import com.exalt.mycompany.model.Role;
import com.exalt.mycompany.model.User;
import com.exalt.mycompany.model.UserProfile;
import com.exalt.mycompany.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RoleServiceImp implements RoleService{

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles =  (List<Role>) roleRepository.findAll();
        if(roles.size() == 0) {
            throw new EntityNotFoundException("No Roles Found");

        }
        return roles;
    }

    @Override
    public Role getRoleWithID(int id) {
        return roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Role With ID={" + id + "} Was Found"));
    }

    @Override
    public void createNewRole(Role r) {
        roleRepository.save(r);
    }

    @Override
    @Transactional
    public void updateRole(int id, Role r) {
        getRoleWithID(id);
        r.setId(id);
        roleRepository.save(r);
    }

    @Override
    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }
}
