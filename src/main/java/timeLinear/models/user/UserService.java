package timeLinear.models.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import timeLinear.models.auth.ChangePasswordRequest;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    public List<UserGroup> getAllUsers() {
        User user  = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<User> allUsers = repository.findAll().stream().filter(
                (value) -> !Objects.equals(value.getId(), user.getId())).toList();
        return allUsers.stream().map(UserGroup::new).collect(Collectors.toList());
    }

    public UserResponse changeAvatar(UserAvatarRequest userAvatarRequest) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOptional = repository.findByEmail(loggedUser.getEmail());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setAvatarSeed(userAvatarRequest.getAvatarSeed());
            user.setAvatarType(userAvatarRequest.getAvatarType());
            repository.save(user);
            return new UserResponse(user);
        }
        return null;
    }

    public void changePassword(ChangePasswordRequest request, User connectedUser) {

        if (!passwordEncoder.matches(request.getCurrentPassword(), connectedUser.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        connectedUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
        repository.save(connectedUser);
    }
}