package timeLinear.models.timeEvent;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import timeLinear.models.timeline.Timeline;
import timeLinear.models.user.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "TIME_EVENT")
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

    @ManyToMany
    @JoinTable(
            name = "TIMELINE_TIMEEVENT",
            joinColumns = @JoinColumn(name = "timeevent_id"),
            inverseJoinColumns = @JoinColumn(name = "timeline_id")
    )
    private List<Timeline> timelines = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    public TimeEvent (TimeEventRequest timeEventBean) {
        this.name = timeEventBean.getName();
        this.description = timeEventBean.getDescription();
        this.startDate = timeEventBean.getStartDate();
        this.endDate = timeEventBean.getEndDate();
        this.owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
