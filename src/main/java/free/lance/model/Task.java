package free.lance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
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
    private String name;

    // Описание
    @Column
    private String description;

    // Заказчик
    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private User customer;

    // Категории
    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @JoinTable(
            name = "tasks_categories",
            joinColumns = @JoinColumn( name = "task_id" ),
            inverseJoinColumns = @JoinColumn( name = "category_id" )
    )
    private List<Category> categories;

    // Бюджет
    @Column
    private Integer budget;

    // Дата создания
    @Column
    private LocalDate createdAt;

    // Дата окончания
    @Column
    private LocalDate deadline;

    // Сколько отведено на выполнение (в секундах)
    @Column
    private Long expires;

    // Способ оплаты
    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private PaymentMethod paymentMethod;

    // Статус
    @Enumerated( EnumType.STRING )
    private Status status = Status.PROCESS;

    // Ссылки
    @ElementCollection
    private Set<String> links;

    // Документы
    @ElementCollection
    private Set<Doc> docs;
}
