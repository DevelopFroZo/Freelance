package free.lance.domain.service;

import free.lance.domain.model.Solution;
import free.lance.domain.repository.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolutionService{
    @Autowired
    private SolutionRepository solutionRepository;

    public Solution findOneByTaskIdAndUserId( Long taskId, Long userId ){
        return this.solutionRepository.findOneByTaskIdAndUserId( taskId, userId );
    }

    public boolean save( Solution solution ){
        try{
            this.solutionRepository.save( solution );

            return true;
        } catch( Exception e ) {
            System.out.println( e );

            return false;
        }
    }
}
