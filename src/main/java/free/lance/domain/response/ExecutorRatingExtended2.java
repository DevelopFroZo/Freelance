package free.lance.domain.response;

import free.lance.domain.model.Category;
import free.lance.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExecutorRatingExtended2 {
    private User user;
    private Category category;
    private Double rating;
}
