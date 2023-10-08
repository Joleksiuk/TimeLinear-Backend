package timeLinear.models.timeEvent;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "TIMEVENT")
@NoArgsConstructor
public class TimeEvent {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use GenerationType.IDENTITY for auto-generated IDs
    private Long id;

    @Column(name = "NAME")
    private String name;

    public TimeEvent (TimeEventBean timeEventBean) {
        this.name = timeEventBean.getName();
    }
}
