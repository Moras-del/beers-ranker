package pl.moras.auth.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.moras.auth.models.Role;
import pl.moras.auth.models.User;


import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    boolean existsByUsername(String username);
}
