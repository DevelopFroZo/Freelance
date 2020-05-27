package free.lance.domain.service;

import free.lance.domain.model.ExecutorRating;
import free.lance.domain.repository.ExecutorRatingRepository;
import free.lance.domain.response.ExecutorRatingExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ExecutorRatingService{
    @Autowired
    private ExecutorRatingRepository executorRatingRepository;

    public Set<ExecutorRatingExtended> findAllByUserIds( Set<Long> userIds ){
        return this.executorRatingRepository.findAllByUserIn( userIds );
    }

    public List<ExecutorRating> findAll(){
        return this.executorRatingRepository.findAll();
    }
}
