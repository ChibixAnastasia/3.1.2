package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;


@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleByRoleName(String role) {
        return roleRepository.getRoleByRoleName(role);
    }

    @Override
    public HashSet<Role> getSetOfRoles(String[] roleNames) {
        HashSet<Role> roleSet = new HashSet<>();
        for(String role : roleNames){
            roleSet.add(getRoleByRoleName(role));
        }
        return roleSet;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}