package free.lance.web.controller;

import free.lance.domain.model.Solution;
import free.lance.domain.model.Task;
import free.lance.domain.model.User;
import free.lance.domain.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping( value = "/solutions" )
public class SolutionController{
    @Autowired
    private SolutionService solutionService;

    @RequestMapping( value = "get_by_task_id" )
    @PreAuthorize( "hasRole( 'ROLE_USER' )" )
    @ResponseBody
    public Set<Solution> getByTask(
            @RequestParam( "task_id" ) Set<Solution> solutions
    ){
        return solutions;
    }

    @RequestMapping( value = "add" )
    @PreAuthorize( "hasRole( 'ROLE_USER' )" )
    public String addGet(
            @RequestParam( "task_id" ) Task task,
            Model model,
            Authentication authentication
    ){
        User executor = (User) authentication.getPrincipal();

        // FIXME change to error
        if( task.getCustomer().getId() == executor.getId() );

        Solution solution = this.solutionService.findOneByTaskIdAndUserId( task.getId(), executor.getId() );

        // FIXME change to error
        if( solution != null );

        model.addAttribute( "solution", new Solution() );

        return "solutions/add";
    }

    @RequestMapping( value = "/", method = RequestMethod.POST )
    @PreAuthorize( "hasRole( 'ROLE_USER' )" )
    public String addPost(
            @Valid @ModelAttribute( "solution" ) Solution solution,
            BindingResult errors,
            @RequestParam( "task_id" ) Task task,
            Authentication authentication
    ){
        User executor = (User) authentication.getPrincipal();

        // FIXME change to error
        if( task.getCustomer().getId() == executor.getId() );

        Solution solution_ = this.solutionService.findOneByTaskIdAndUserId( task.getId(), executor.getId() );

        // FIXME change to error
        if( solution_ != null );

        solution.setTask( task );
        solution.setExecutor( executor );
        System.out.println( solution );
        boolean isSaved = this.solutionService.save( solution );

        // FIXME if error send to error page

        return "redirect:/tasks/task?id=" + task.getId();
    }
}
