package free.lance.domain.service;

import free.lance.domain.model.Task;
import free.lance.domain.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService{
    @Autowired
    private TaskRepository taskRepository;

    public Page<Task> findAll( Pageable page ){
        return this.taskRepository.findAll( page );
    }
}
