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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STARTDATE")
    private String startDate;

    @Column(name = "ENDDATE")
    private String endDate;

    public TimeEvent (TimeEventBean timeEventBean) {
        this.name = timeEventBean.getName();
        this.description = timeEventBean.getDescription();
        this.startDate = timeEventBean.getStartDate();
        this.endDate = timeEventBean.getEndDate();
    }
}
