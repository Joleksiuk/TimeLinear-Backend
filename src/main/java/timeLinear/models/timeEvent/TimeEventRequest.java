package timeLinear.models.timeEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TimeEventRequest {
    private String name;
    private String description;
    private String startDate;
    private String endDate;
}
