package free.lance.domain.service;

import free.lance.domain.repository.ExecutorRatingRepository;
import free.lance.domain.response.ExecutorRatingExtended;
import free.lance.domain.response.ExecutorRatingExtended2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ExecutorRatingService{
    @Autowired
    private ExecutorRatingRepository executorRatingRepository;

    public Set<ExecutorRatingExtended> findAllByUserIds( Set<Long> userIds ){
        return this.executorRatingRepository.findAllByUserIn( userIds );
    }

    public Page<ExecutorRatingExtended2> findAll( Pageable page ){
        return this.executorRatingRepository.findAllExtended( page );
    }
}
