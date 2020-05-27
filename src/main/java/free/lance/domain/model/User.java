package free.lance.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table( name = "users" )
public class User implements UserDetails{
    // ID
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    // Роль
    @Enumerated( EnumType.STRING )
    private UserRole role;

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
    @Column( unique = true )
    private String login;

    // Пароль
    @Column
    private String password;

    // Баланс
    @Column
    private Long balance;

    // Рейтинг пользователя, как заказчика
    // Хранится массивом чисел от 1 до 5
    // Считается следующиим образом:
    // Суммируются все числа и делятся на длину массиваs
    @ElementCollection( fetch = FetchType.EAGER )
    @JoinTable( name = "user_customer_rating_values" )
    private List<Integer> customerRating;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return AuthorityUtils.createAuthorityList( this.role.toString() );
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername(){
        return this.login;
    }
}
