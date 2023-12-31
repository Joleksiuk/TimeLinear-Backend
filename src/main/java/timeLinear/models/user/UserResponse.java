package timeLinear.models.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String email;
    private String username;
    private String avatarSeed;
    private  String avatarType;

    public UserResponse(User user){
        this.id = user.getId().longValue();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.avatarSeed = user.getAvatarSeed();
        this.avatarType = user.getAvatarType();
    }
}
