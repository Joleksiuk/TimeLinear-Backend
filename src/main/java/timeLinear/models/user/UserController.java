package timeLinear.models.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import timeLinear.models.auth.ChangePasswordRequest;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService service;

    @PostMapping("changePassword")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        service.changePassword(request, loggedUser);
        return ResponseEntity.ok().build();
    }

    @PutMapping("changeAvatar")
    public ResponseEntity<UserResponse> changeAvatar(@RequestBody UserAvatarRequest userAvatarRequest){
        UserResponse userResponse = service.changeAvatar(userAvatarRequest);
        return ResponseEntity.ok().body(userResponse);
    }

    @GetMapping
    public ResponseEntity<UserGroupBulk> getAllUsers() {
        UserGroupBulk response = new UserGroupBulk(service.getAllUsers());
        return ResponseEntity.ok().body(response);
    }
}