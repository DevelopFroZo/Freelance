package free.lance.web.controller;

import free.lance.domain.model.Solution;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
@RequestMapping( value = "/solutions" )
public class SolutionController{
    @RequestMapping( value = "get_by_task_id" )
    @PreAuthorize( "hasRole( 'ROLE_USER' )" )
    @ResponseBody
    public Set<Solution> getByTask(
            @RequestParam( "task_id" ) Set<Solution> solutions
    ){
        return solutions;
    }
}
