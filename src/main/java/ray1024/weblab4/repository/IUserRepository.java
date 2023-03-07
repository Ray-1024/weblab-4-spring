package ray1024.weblab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ray1024.weblab4.data.User;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findBySessionID(String sessionID);
}