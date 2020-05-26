package free.lance.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table( name = "tasks" )
public class Task{
    // ID
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    // Название
    @Column
    @NotBlank( message = "Must be not empty" )
    private String name;

    // Описание
    @Column
    @NotBlank( message   = "Must be not empty" )
    private String description;

    // Заказчик
    @ManyToOne( fetch = FetchType.EAGER )
    private User customer;

    // Категории
    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable(
            name = "tasks_categories",
            joinColumns = @JoinColumn( name = "task_id" ),
            inverseJoinColumns = @JoinColumn( name = "category_id" )
    )
    @NotNull( message = "Select at least one" )
    private Set<Category> categories;

    // Бюджет
    @Column
    @NotNull( message = "Minimum is 1" )
    @Min( value = 1, message = "Minimum is 1" )
    private Integer budget;

    // Дата создания
    @Column
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private LocalDate createdAt;

    // Дата окончания
    @Column
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private LocalDate deadline;

    // Способ оплаты
    @ManyToOne( fetch = FetchType.EAGER )
    private PaymentMethod paymentMethod;

    // Статус
    @Enumerated( EnumType.STRING )
    private Status status = Status.PROCESS;

    // Ссылки
    @ElementCollection( fetch = FetchType.EAGER )
    private Set<String> links;

    // Документы
    @ElementCollection( fetch = FetchType.EAGER )
    private Set<Doc> docs;

    // Отмеченное решение
    @ManyToOne( fetch = FetchType.EAGER )
    private Solution solution;
}
