package free.lance.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExecutorRatingExtended{
    private Long userId;
    private String category;
    private Double rating;
}
