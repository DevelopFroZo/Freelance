package free.lance.domain.converter;

import free.lance.domain.model.Solution;
import free.lance.domain.repository.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class TaskIdToSolutions implements Converter<Long, List<Solution>>{
    @Autowired
    private SolutionRepository solutionRepository;

    @Override
    public List<Solution> convert(Long taskId ){
        return this.solutionRepository.findAllByTaskId( taskId );
    }
}
