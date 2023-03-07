package ray1024.weblab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ray1024.weblab4.data.Point;
import ray1024.weblab4.data.User;

import java.util.Collection;

public interface IPointRepository extends JpaRepository<Point, Long> {
    Collection<Point> findAllByOwner(User user);
}
