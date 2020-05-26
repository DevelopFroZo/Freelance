package free.lance.domain.repository;

import free.lance.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByLogin( String login );

    @Modifying
    @Transactional
    @Query( "update User as u set u.balance = u.balance - :value where u.id = :id" )
    void decBalance(
            @Param( "value" ) Long value,
            @Param( "id" ) Long id
    );

    @Modifying
    @Transactional
    @Query( "update User as u set u.balance = u.balance + :value where u.id = :id" )
    void incBalance(
            @Param( "id" ) Long id,
            @Param( "value" ) Long value
    );
}
