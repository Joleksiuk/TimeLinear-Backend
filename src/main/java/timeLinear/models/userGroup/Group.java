package timeLinear.models.userGroup;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import timeLinear.models.timeEvent.TimeEventRequest;
import timeLinear.models.user.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "_group")
@NoArgsConstructor
public class Group {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "USER_GROUP",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    public Group (GroupRequest groupRequest) {
        this.name = groupRequest.getName();
        this.description = groupRequest.getDescription();
        this.owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
