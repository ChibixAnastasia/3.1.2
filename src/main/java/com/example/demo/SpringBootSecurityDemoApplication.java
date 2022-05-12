package com.example.demo;


import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;



import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class SpringBootSecurityDemoApplication extends SpringBootServletInitializer implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public SpringBootSecurityDemoApplication(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
    }



    @Override
    public void run(String... args) throws Exception {
        roleService.addRole(new Role(1L,"ADMIN"));
        roleService.addRole(new Role(2L,"USER"));

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleService.getRoleByRoleName("ADMIN"));
        roleSet.add(roleService.getRoleByRoleName("USER"));

        Set<Role> roleSetUser = new HashSet<>();
        roleSetUser.add(roleService.getRoleByRoleName("USER"));

        User admin = new User("admin", 22,"admin",roleSet);

        User user = new User("user", 33,"user",roleSetUser);


        userService.saveUser(admin);
        userService.saveUser(user);

        openLoginPage();
    }

    private static void openLoginPage() {
        try {
            Runtime.getRuntime().exec("cmd /c start http://localhost:8080/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SpringApplicationBuilder configure (SpringApplicationBuilder applicationBuilder){
        return applicationBuilder.sources(SpringBootSecurityDemoApplication.class);
    }
}