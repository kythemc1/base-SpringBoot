package vn.siten.base.data.repository;

import vn.siten.base.data.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeReps extends JpaRepository<Privilege, Long> {
    Privilege findByName(String name);
}
