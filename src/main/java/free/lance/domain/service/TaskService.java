package free.lance.domain.service;

import free.lance.domain.model.Task;
import free.lance.domain.response.TaskCard;
import free.lance.domain.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
}
