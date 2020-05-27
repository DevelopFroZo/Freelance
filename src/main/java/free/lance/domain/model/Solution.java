package free.lance.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table( name = "solutions" )
public class Solution{
    // ID
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    // Задание
    @ManyToOne( fetch = FetchType.EAGER )
    private Task task;

    // Описание
    @Column
    @NotBlank( message = "Must be not empty" )
    private String description;

    // Ссылки
    @ElementCollection( fetch = FetchType.EAGER )
    private Set<String> links;

    // Документы
    @ElementCollection( fetch = FetchType.EAGER )
    private Set<Doc> docs;

    // Исполнитель
    @ManyToOne( fetch = FetchType.EAGER )
    private User executor;
}
