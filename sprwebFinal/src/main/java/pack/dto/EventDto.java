package pack.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eNo;

    private String eTitle;
    private String eImageUrl;
    private LocalDateTime eDate;
    private String eLocation;
    private String eCategory;

    public EventDto toDto() {
        return EventDto.builder()
                .eNo(this.eNo)
                .eTitle(this.eTitle)
                .eImageUrl(this.eImageUrl)
                .eDate(this.eDate)
                .eLocation(this.eLocation)
                .eCategory(this.eCategory)
                .build();
    }
}



