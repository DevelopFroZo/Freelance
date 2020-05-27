package free.lance.domain.response;

import free.lance.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserExtended{
    private User full;
    private Double rating;
}
