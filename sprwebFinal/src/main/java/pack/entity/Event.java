package pack.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eNo;

    private String eTitle;
    private String eImageUrl;
    private LocalDateTime eDate;
    private String eLocation;
    private String eCategory;
}