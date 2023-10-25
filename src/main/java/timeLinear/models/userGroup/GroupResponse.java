package timeLinear.models.userGroup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import timeLinear.models.user.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponse {
    private Long id;
    private String name;
    private String description;

    private List<UserResponse> users;

    public GroupResponse (Group group){
        this.id = group.getId();
        this.name = group.getName();
        this.description = group.getDescription();
        this.users = group.getUsers().stream().map(UserResponse::new).collect(Collectors.toList());
    }
}
