package pl.moras.auth.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.moras.auth.models.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}
