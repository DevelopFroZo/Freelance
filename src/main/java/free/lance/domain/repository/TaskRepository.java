package free.lance.domain.repository;

import free.lance.domain.model.Solution;
import free.lance.domain.model.Task;
import free.lance.domain.response.TaskCard;
import free.lance.domain.response.TaskFull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
    @Query(
            "select new free.lance.domain.response.TaskCard( t, avg( cr ) ) " +
            "from" +
            "   Task as t" +
            "   inner join t.customer.customerRating as cr " +
            "where t.status = free.lance.domain.model.Status.PROCESS " +
            "group by t.id"
    )
    Page<TaskCard> findAllForCard( Pageable page );

    @Query(
            "select new free.lance.domain.response.TaskFull( t, avg( cr ) ) " +
            "from" +
            "   Task as t" +
            "   inner join t.customer.customerRating as cr " +
            "where t.id = :id " +
            "group by t.id"
    )
    TaskFull findOneExtended( @Param( "id" ) Long id );

    @Modifying
    @Transactional
    @Query( "update Task as t set t.solution = :solution where t.id = :id" )
    void setSolution( @Param( "id" ) Long id, @Param( "solution" ) Solution solution );

    @Modifying
    @Transactional
    @Query( "update Task as t set t.status = free.lance.domain.model.Status.CLOSED where t.id = :id" )
    void close( @Param( "id" ) Long id );
}
