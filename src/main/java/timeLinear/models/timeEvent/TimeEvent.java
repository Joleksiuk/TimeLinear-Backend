package timeLinear.models.timeEvent;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import timeLinear.models.timeline.Timeline;
import timeLinear.models.user.User;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "TIMELINE_TIMEEVENT",
            joinColumns = @JoinColumn(name = "timeevent_id"),
            inverseJoinColumns = @JoinColumn(name = "timeline_id")
    )
    private List<Timeline> timelines = new ArrayList<>();

    public TimeEvent (TimeEventRequest timeEventBean) {
        this.name = timeEventBean.getName();
        this.description = timeEventBean.getDescription();
        this.startDate = timeEventBean.getStartDate();
        this.endDate = timeEventBean.getEndDate();
    }
}
