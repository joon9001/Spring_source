package pack.dto;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pack.entity.Event;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventEntity {

    private Integer eNo;
    private String eTitle;
    private String eImageUrl;
    private LocalDateTime eDate;
    private String eLocation;
    private String eCategory;

    public Event toEntity() {
        return Event.builder()
                .eNo(this.eNo)
                .eTitle(this.eTitle)
                .eImageUrl(this.eImageUrl)
                .eDate(this.eDate)
                .eLocation(this.eLocation)
                .eCategory(this.eCategory)
                .build();
    }
}
