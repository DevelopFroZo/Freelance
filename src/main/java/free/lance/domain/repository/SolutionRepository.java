package free.lance.domain.repository;

import free.lance.domain.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolutionRepository extends JpaRepository<Solution, Long>{
    @Query(
            "select s from Solution as s where s.task.id = :id"
    )
    List<Solution> findAllByTaskId( @Param( "id" ) Long taskId );
}
