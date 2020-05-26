package free.lance.domain.response;

import free.lance.domain.model.Category;
import free.lance.domain.model.Task;
import free.lance.domain.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class TaskCard{
    private Long id;
    private String name;
    private String description;
    private Set<Category> categories;
    private User customer;
    private Integer budget;
    private Double rating;

    public TaskCard(Task task, Double rating ){
        this.id = task.getId();
        this.name = task.getName();
        this.description = task.getDescription();
        this.categories = task.getCategories();
        this.customer = task.getCustomer();
        this.budget = task.getBudget();
        this.rating = rating;
    }
}
