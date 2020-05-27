package free.lance.web.controller;

import free.lance.domain.model.*;
import free.lance.domain.response.TaskFull;
import free.lance.domain.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping( value = "/tasks" )
public class TaskController{
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @RequestMapping( value = "/task" )
    private String task(
            @RequestParam( "id" ) TaskFull taskFull,
            Model model
    ){
        taskFull.getFull().setSolution( null );
        model.addAttribute( "task", taskFull );

        return "tasks/task";
    }

    @PreAuthorize( "hasRole( 'ROLE_USER' )" )
    @RequestMapping( value = "/add" )
    public String addGet( Model model ){
        Task task = new Task();
        List<Category> categories = this.categoryService.findAll();
        List<PaymentMethod> paymentMethods = this.paymentMethodService.findAll();

        model.addAttribute( "task", task );
        model.addAttribute( "categories", categories );
        model.addAttribute( "paymentMethods", paymentMethods );

        return "tasks/add";
    }

    @PreAuthorize( "hasRole( 'ROLE_USER' )" )
    @RequestMapping( value = "/", method = RequestMethod.POST )
    public String addPost(
            @Valid @ModelAttribute( "task" ) Task task,
            BindingResult errors,
            Model model,
            Authentication authentication
    ){
        User customer = (User) authentication.getPrincipal();

        if( task.getBudget() != null && customer.getBalance() < task.getBudget() )
            errors.rejectValue( "budget", "error.task", "Your balance less then sended budget" );

        if( errors.hasErrors() ){
            List<Category> categories = this.categoryService.findAll();
            List<PaymentMethod> paymentMethods = this.paymentMethodService.findAll();

            model.addAttribute( "categories", categories );
            model.addAttribute( "paymentMethods", paymentMethods );

            return "tasks/add";
        }

        task.setCustomer( customer );
        taskService.save( task );
        this.userService.decBalance( Long.valueOf( task.getBudget() ), customer.getId() );

        return "redirect:/";
    }

    @PreAuthorize( "hasRole( 'ROLE_USER' )" )
    @RequestMapping( "/set_solution" )
    @ResponseBody
    public boolean setSolution(
            @RequestParam( "task_id" ) Task task,
            @RequestParam( "solution_id" ) Solution solution,
            Authentication authentication
    ){
        this.taskService.setSolution( task.getId(), solution );
        this.taskService.close( task.getId() );
        this.userService.incBalance( solution.getExecutor().getId(), Long.valueOf( task.getBudget() ) );

        return true;
    }
}
