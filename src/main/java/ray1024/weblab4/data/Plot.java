package ray1024.weblab4.data;

import org.springframework.stereotype.Component;

@Component
public class Plot {
    public boolean check(double x, double y, double r) {
        if (r < 0) {
            r = -r;
            x = -x;
            y = -y;
        }
        return (x >= -r && x <= 0.0 && y <= 0.0 && y * y <= r * r - x * x) || (x >= 0.0 && x <= r && y >= 0.0 && y <= r - x) || (x >= 0.0 && x <= r && y <= 0.0 && y >= -r / 2.0);
    }

    public boolean check(Point point) {
        return check(point.getX(), point.getY(), point.getR());
    }

}
