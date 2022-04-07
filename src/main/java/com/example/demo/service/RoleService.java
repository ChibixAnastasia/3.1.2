package com.example.demo.service;

import com.example.demo.model.Role;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
@Service
public interface RoleService {
    Role addRole(Role role);
    Role getRoleByRoleName(String role);
    HashSet<Role> getSetOfRoles(String[] roleNames);
    List<Role> getAllRoles();
}
