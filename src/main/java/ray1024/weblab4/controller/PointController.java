package ray1024.weblab4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ray1024.weblab4.data.LightPoint;
import ray1024.weblab4.data.Plot;
import ray1024.weblab4.data.Point;
import ray1024.weblab4.data.User;
import ray1024.weblab4.repository.IPointRepository;
import ray1024.weblab4.repository.IUserRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;

@RestController
public class PointController {
    private final IPointRepository pointRepository;
    private final IUserRepository userRepository;
    private final Plot plot;

    public PointController(@Autowired IPointRepository pointRepository, @Autowired IUserRepository userRepository, @Autowired Plot plot) {
        this.pointRepository = pointRepository;
        this.userRepository = userRepository;
        this.plot = plot;
    }

    @CrossOrigin
    @GetMapping("/points")
    Collection<LightPoint> points(HttpSession session) {
        User user = userRepository.findBySessionID(session.getId());
        if (user != null) {
            Collection<Point> pointCollection = pointRepository.findAllByOwner(user);
            ArrayList<LightPoint> ans = new ArrayList<>(pointCollection.size());
            for (Point i : pointCollection) {
                LightPoint lightPoint = new LightPoint();
                lightPoint.setX(i.getX());
                lightPoint.setY(i.getY());
                ans.add(lightPoint);
            }
            return ans;
        }
        return null;
    }

    @CrossOrigin
    @PostMapping("/add_point")
    String addPoint(HttpSession session, @RequestBody Point point) {
        User user = userRepository.findBySessionID(session.getId());
        if (user != null) {
            point.setResult(plot.check(point));
            point.setOwner(user);
            pointRepository.save(point);
            return "{\"result\":\"OK\"}";
        }
        return "{\"result\":\"NO\"}";
    }

}
