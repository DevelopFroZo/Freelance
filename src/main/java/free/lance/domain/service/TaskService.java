package free.lance.domain.service;

import free.lance.domain.model.Solution;
import free.lance.domain.model.Task;
import free.lance.domain.response.TaskCard;
import free.lance.domain.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService{
    @Autowired
    private TaskRepository taskRepository;

    public Page<TaskCard> findAllForCard( Pageable page ){
        return this.taskRepository.findAllForCard( page );
    }

    public boolean save( Task task ){
        task.setCreatedAt( LocalDate.now() );

        try{
            this.taskRepository.save( task );
        } catch( Exception e ){
            System.out.println( e );

            return false;
        }

        return true;
    }

    public void setSolution( Long id, Solution solution ){
        this.taskRepository.setSolution( id, solution );
    }

    public void close( Long id ){
        this.taskRepository.close( id );
    }

    public List<Task> findAllByUserId( Long userId ){
        return this.taskRepository.findAllByUserId( userId );
    }
}
