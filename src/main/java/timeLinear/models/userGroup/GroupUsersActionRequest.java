package timeLinear.models.userGroup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroupUsersActionRequest {
    private List<Long> usersIds;
    private Long groupId;
}
