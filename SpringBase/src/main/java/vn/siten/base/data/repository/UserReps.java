package vn.siten.base.data.repository;

import vn.siten.base.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReps extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
