package timeLinear.models.userGroup;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import timeLinear.models.timeline.Timeline;
import timeLinear.models.timeline.TimelineResponse;
import timeLinear.models.user.User;
import timeLinear.models.user.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
public class GroupController {

    @Autowired
    private final GroupRepository groupRepository;

    @Autowired
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<GroupResponse> createGroup(@RequestBody GroupRequest data) {
        try{
            Group group = groupRepository.save(new Group(data));
            return ResponseEntity.ok().body(new GroupResponse(group));
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<String> deleteGroup(@PathVariable Long groupId) {
        try{
            Optional<Group> groupOptional = groupRepository.findById(groupId);
            groupOptional.ifPresent(groupRepository::delete);
            return ResponseEntity.ok().body("Group deleted!");
        }
        catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<String> updateGroup(@PathVariable Long groupId, @RequestBody GroupRequest groupRequest) {
        Optional<Group> groupOptional = groupRepository.findById(groupId);
        if (groupOptional.isPresent()) {
            Group updatedGroup = new Group(groupRequest);
            updatedGroup.setId(groupId);
            groupRepository.save(updatedGroup);
            return ResponseEntity.ok().body("Group updated!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/addUser")
    public ResponseEntity<String> addUserToGroup(@RequestBody UserGroupRequest userGroupRequest) {
        Optional<Group> groupOptional = groupRepository.findById(userGroupRequest.getGroupId());
        Optional<User> userOptional = userRepository.findById(userGroupRequest.getUserId().intValue());

        if (groupOptional.isPresent() && userOptional.isPresent()) {
            Group updatedGroup = groupOptional.get();
            updatedGroup.getUsers().add(userOptional.get());
            groupRepository.save(updatedGroup);
            return ResponseEntity.ok().body("User added to group!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/removeUser")
    public ResponseEntity<String> removeUserFromGroup(@RequestBody UserGroupRequest userGroupRequest) {
        Optional<Group> groupOptional = groupRepository.findById(userGroupRequest.getGroupId());
        Optional<User> userOptional = userRepository.findById(userGroupRequest.getUserId().intValue());

        if (groupOptional.isPresent() && userOptional.isPresent()) {
            Group updatedGroup = groupOptional.get();
            updatedGroup.getUsers().remove(userOptional.get());
            groupRepository.save(updatedGroup);
            return ResponseEntity.ok().body("User removed from group!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponse> getGroup(@PathVariable Long id) {
        Optional<Group> groupOptional = groupRepository.findById(id);
        return groupOptional.map(group -> ResponseEntity.ok().body(new GroupResponse(group)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/bulk")
    public ResponseEntity<List<GroupResponse>> getGroupsInBulk(@RequestBody GroupBulkRequest groupBulkRequest) {
        List<Group> groups = groupRepository.findAllById(groupBulkRequest.getGroupIds());
        return  ResponseEntity.ok().body(groups.stream().map(GroupResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/user")
    public ResponseEntity<List<GroupResponse>> getGroupsOfUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Group> groups = groupRepository.findAllByOwner(user);
        return ResponseEntity.ok().body(groups.stream().map(GroupResponse::new).collect(Collectors.toList()));
    }
}
