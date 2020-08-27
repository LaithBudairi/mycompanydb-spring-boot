package com.exalt.mycompany.service;

import com.exalt.mycompany.model.Role;
import com.exalt.mycompany.model.User;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author LaithB
 */
public interface RoleService {

    /**
     * @return all roles existing in the database.
     * @throws EntityNotFoundException if no roles found
     */
    List<Role> getAllRoles();

    /**
     *
     * @param id role id to search for
     * @return role with the specified id
     * @throws EntityNotFoundException if the role with the specified id is not found
     */
    Role getRoleWithID(int id);

    /**
     *
     * @param r role to create
     */
    void createNewRole(Role r);

    /**
     *
     * @param id role id to update its info
     * @param r info to update role
     * @return void
     * @throws EntityNotFoundException if the role with the specified id is not found
     */
    void updateRole(int id, Role r);
    /**
     *
     * @param id role id to delete
     * @return void
     */
    void deleteRole(int id);

}
