package timeLinear.models.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import timeLinear.models.auth.ChangePasswordRequest;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/changePassword")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request) {
        Principal user  = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        service.changePassword(request, user);
        return ResponseEntity.ok().build();
    }
}