package free.lance.domain.model;

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
    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private User customer;

    // Категории
    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinTable(
            name = "tasks_categories",
            joinColumns = @JoinColumn( name = "task_id" ),
            inverseJoinColumns = @JoinColumn( name = "category_id" )
    )
    private Set<Category> categories;

    // Бюджет
    @Column
    private Integer budget;

    // Дата создания
    @Column
    private LocalDate createdAt;

    // Дата окончания
    @Column
    private LocalDate deadline;

    // Способ оплаты
    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
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
}
