package timeLinear.models.timeline;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimelinePermissionRequest {
    private Long groupId;
    private Long timelineId;
}
