package free.lance.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table( name = "payment_methods" )
public class PaymentMethod{
    // ID
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    // Название
    @Column
    private String name;
}
