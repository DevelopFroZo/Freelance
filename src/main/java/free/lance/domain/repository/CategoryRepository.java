package free.lance.domain.repository;

import free.lance.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    Set<Category> findAllByIdIn( @Param( "ids" ) Set<Long> ids );
}
