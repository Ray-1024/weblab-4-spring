package ray1024.weblab4.data;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "points")
public class Point extends LightPoint {
    @Id
    @GeneratedValue
    private long id;
    double x, y, r;
    boolean result;
    @ManyToOne
    private User owner;
}
