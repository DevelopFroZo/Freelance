package free.lance.domain.repository;

import free.lance.domain.model.User;
import free.lance.domain.response.UserExtended;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(
            "select new free.lance.domain.response.UserExtended( u, avg( ucr ) ) " +
            "from" +
            "   User as u" +
            "   inner join u.customerRating as ucr " +
            "group by u"
    )
    Page<UserExtended> findAllExtended( Pageable page );
}
