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
@Table(name = "_GROUP")
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
            name = "_USER_GROUP",
            joinColumns = @JoinColumn(name = "GROUP_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID")
    )
    private List<User> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User owner;

    public Group (GroupRequest groupRequest) {
        this.name = groupRequest.getName();
        this.description = groupRequest.getDescription();
        this.owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
