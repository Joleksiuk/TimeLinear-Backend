package timeLinear.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserGroup {
    private Long id;
    private String username;
    private String avatarSeed;
    private String avatarType;

    public UserGroup(User user) {
        this.id = user.getId().longValue();
        this.username = user.getUsername();
        this.avatarSeed = user.getAvatarSeed();
        this.avatarType = user.getAvatarType();
    }
}
