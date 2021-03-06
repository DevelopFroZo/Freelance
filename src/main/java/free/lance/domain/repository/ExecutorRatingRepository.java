package free.lance.domain.repository;

import free.lance.domain.model.ExecutorRating;
import free.lance.domain.response.ExecutorRatingExtended;
import free.lance.domain.response.ExecutorRatingExtended2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ExecutorRatingRepository extends JpaRepository<ExecutorRating, Long>{
    @Query(
            "select new free.lance.domain.response.ExecutorRatingExtended( er.user.id, er.category.name, avg( erv ) ) " +
            "from" +
            "   ExecutorRating as er" +
            "   inner join er.rating as erv " +
            "where er.user.id in ( :userIds ) " +
            "group by er.user.id, er.category"
    )
    Set<ExecutorRatingExtended> findAllByUserIn( @Param( "userIds" ) Set<Long> userIds );

    @Query(
            "select new free.lance.domain.response.ExecutorRatingExtended2( er.user, er.category, avg( erv ) ) " +
            "from" +
            "   ExecutorRating as er" +
            "   inner join er.rating as erv " +
            "group by er.user.id, er.category"
    )
    Page<ExecutorRatingExtended2> findAllExtended( Pageable page );
}
