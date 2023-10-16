package timeLinear.models.timeEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TimeEventResponse {
    private Long id;
    private String name;
    private String description;
    private String startDate;
    private String endDate;

    public TimeEventResponse (TimeEvent timeEvent){
        this.id = timeEvent.getId();
        this.name = timeEvent.getName();
        this.description = timeEvent.getDescription();
        this.startDate = timeEvent.getStartDate();
        this.endDate = timeEvent.getEndDate();
    }
}
