package vn.siten.base.data.repository;

import vn.siten.base.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleReps extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
