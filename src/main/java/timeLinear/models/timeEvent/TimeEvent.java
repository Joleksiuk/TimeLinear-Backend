package timeLinear.models.timeEvent;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import timeLinear.models.user.User;
import timeLinear.models.userGroup.Group;

@Entity
@Data
@Table(name = "_TIME_EVENT")
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

    @Column(name = "ICON_TYPE")
    @Nullable
    private String iconType;

    @Column(name = "ICON_SOURCE")
    @Nullable
    private String iconSource;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToOne
    @JoinColumn(name = "group_id")
    private Group allowedToBrowse;

    public TimeEvent (TimeEventRequest timeEventBean) {
        this.name = timeEventBean.getName();
        this.description = timeEventBean.getDescription();
        this.startDate = timeEventBean.getStartDate();
        this.endDate = timeEventBean.getEndDate();
        this.iconType = timeEventBean.getIconType();
        this.iconSource = timeEventBean.getIconSource();
        this.owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
