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

    public UserResponse(User user){
        this.id = user.getId().longValue();
        this.email = user.getEmail();
    }
}
