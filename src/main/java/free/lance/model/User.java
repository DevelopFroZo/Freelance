package free.lance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table( name = "users" )
public class User{
    // ID
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    // Дата регистрации
    @Column
    private LocalDate registeredAt;

    // Имя
    @Column
    private String name;

    // О себе
    @Column
    private String description;

    // Логин
    @Column
    private String login;

    // Пароль
    @Column
    private String password;

    // Баланс
    @Column
    private Long balance = 0L;

    // Рейтинг пользователя, как заказчика
    // Хранится массивом чисел от 1 до 5
    // Считается следующиим образом:
    // Суммируются все числа и делятся на длину массиваы
    @ElementCollection
    @JoinTable( name = "user_customer_rating_values" )
    private List<Integer> customerRating;
}
