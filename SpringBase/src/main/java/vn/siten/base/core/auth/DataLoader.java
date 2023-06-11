package vn.siten.base.core.auth;

import vn.siten.base.data.entity.Privilege;
import vn.siten.base.data.entity.Role;
import vn.siten.base.data.entity.User;
import vn.siten.base.data.repository.PrivilegeReps;
import vn.siten.base.data.repository.RoleReps;
import vn.siten.base.data.repository.UserReps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class DataLoader {
    public class SetupDataLoader implements
            ApplicationListener<ContextRefreshedEvent> {

        boolean alreadySetup = false;

        @Autowired
        private UserReps userRepository;

        @Autowired
        private RoleReps roleRepository;

        @Autowired
        private PrivilegeReps privilegeRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        @Transactional
        public void onApplicationEvent(ContextRefreshedEvent event) {
            if (alreadySetup)
                return;
            Privilege readPrivilege
                    = createPrivilegeIfNotFound("READ_PRIVILEGE");
            Privilege writePrivilege
                    = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

            List<Privilege> adminPrivileges = Arrays.asList(
                    readPrivilege, writePrivilege);
            createRoleIfNotFound("admin", adminPrivileges);
            createRoleIfNotFound("user", Arrays.asList(readPrivilege));

            Role adminRole = roleRepository.findByName("ROLE_ADMIN");
            User user = new User();
            user.setFirstName("Test");
            user.setLastName("Test");
            user.setPassword(passwordEncoder.encode("Siten@123#"));
            user.setUsername("admin");
            user.setRoles(Collections.singletonList(adminRole));
            user.setEnabled(true);
            userRepository.save(user);

            alreadySetup = true;
        }

        @Transactional
        Privilege createPrivilegeIfNotFound(String name) {
            Privilege privilege = privilegeRepository.findByName(name);
            if (privilege == null) {
                privilege = new Privilege(){{
                    setName(name);
                }};
                privilegeRepository.save(privilege);
            }
            return privilege;
        }

        @Transactional
        Role createRoleIfNotFound(
                String name, Collection<Privilege> privileges) {

            Role role = roleRepository.findByName(name);
            if (role == null) {
                role = new Role(){{
                    setName(name);
                }};
                role.setPrivileges(privileges);
                roleRepository.save(role);
            }
            return role;
        }
    }
}
