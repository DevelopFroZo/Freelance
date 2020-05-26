package free.lance.domain.converter;

import free.lance.domain.model.Solution;
import free.lance.domain.repository.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class IdToSolution implements Converter<Long, Solution>{
    @Autowired
    private SolutionRepository solutionRepository;

    @Override
    public Solution convert( Long id ){
        return this.solutionRepository.findOne( id );
    }
}
