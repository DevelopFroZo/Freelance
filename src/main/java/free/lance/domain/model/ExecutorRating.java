package free.lance.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table( name = "executor_ratings" )
// Рейтинг пользователей, как исполнителя
// Для каждого пользователя по категории
// хранится массив чисел
// Считается аналогичным способом, как рейтинг
// у заказчика
public class ExecutorRating implements Serializable{
    // Composite ID
    @Id
    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private User user;

    // Composite ID
    @Id
    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private Category category;

    // Рейтинг
    @ElementCollection( fetch = FetchType.EAGER )
    @JoinTable( name = "executor_ratings_values" )
    private List<Integer> rating;
}
