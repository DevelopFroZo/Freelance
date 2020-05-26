package free.lance.domain.converter;

import free.lance.domain.response.TaskFull;
import free.lance.domain.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class IdToTaskFull implements Converter<String, TaskFull>{
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public TaskFull convert( String id ){
        return this.taskRepository.findOneExtended( Long.valueOf( id ) );
    }
}
