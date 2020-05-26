package free.lance.domain.response;

import free.lance.domain.model.Task;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskFull{
    private Task full;
    private Double rating;

    public TaskFull( Task full, Double rating ){
        this.full = full;
        this.rating = rating;
    }
}
