package free.lance.web.controller;

import free.lance.domain.model.Solution;
import free.lance.domain.model.Task;
import free.lance.domain.response.ExecutorRatingExtended;
import free.lance.domain.response.ExecutorRatingExtended2;
import free.lance.domain.response.TaskCard;
import free.lance.domain.model.User;
import free.lance.domain.response.UserExtended;
import free.lance.domain.service.ExecutorRatingService;
import free.lance.domain.service.SolutionService;
import free.lance.domain.service.TaskService;
import free.lance.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class RootController{
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private ExecutorRatingService executorRatingService;

    @Autowired
    private SolutionService solutionService;

    @RequestMapping( value = "/404" )
    public String _404(){
        return "404";
    }

    @RequestMapping( value = "/" )
    public String index(
            Model model,
            Pageable page
    ){
        Page<TaskCard> taskCards;

        taskCards = this.taskService.findAllForCard(
                new PageRequest(
                        page.getPageNumber(),
                        page.getPageSize(),
                        JpaSort.unsafe( Sort.Direction.DESC, "avg( cr )" )
                )
        );

        model.addAttribute( "tasks", taskCards );

        return "index";
    }

    @RequestMapping( value = "/login" )
    public String login(){
        return "login";
    }

    @RequestMapping( value = "/register", method = RequestMethod.GET )
    public String registerGet( Model model ){
        model.addAttribute( new User() );

        return "register";
    }

    @RequestMapping( value = "/register", method = RequestMethod.POST )
    public String register(
            @Valid @ModelAttribute User user,
            BindingResult errors,
            Model model
    ){
        if( errors.hasErrors() )
            return "register";

        boolean isSaved = this.userService.save( user );

        if( !isSaved ){
            errors.rejectValue( "login", "error.user", "Login already picked!" );

            return "register";
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(
                user,
                user.getPassword(),
                user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication( auth );

        return "redirect:/";
    }

    @RequestMapping( value = "personal_account" )
    public String personalAccount(
            Model model,
            Authentication authentication
    ){
        Set<Long> userIds = new HashSet<>();
        User current = (User) authentication.getPrincipal();
        List<Task> userTasks = this.taskService.findAllByUserId( current.getId() );
        List<Solution> solutions = this.solutionService.findAllByUserId( current.getId() );

        userIds.add( current.getId() );
        Set<ExecutorRatingExtended> executorsRatingsExtended = this.executorRatingService.findAllByUserIds( userIds );

        model.addAttribute( "user", current );
        model.addAttribute( "executorRating", executorsRatingsExtended );
        model.addAttribute( "tasks", userTasks );
        model.addAttribute( "solutions", solutions );

        return "personalAccount";
    }

    @RequestMapping( value = "rating_executors" )
    public String ratingExecutors(
            Model model,
            Pageable page
    ){
        Sort sort = page.getSort();
        PageRequest pageRequest;

        if( sort == null ) pageRequest = new PageRequest( page.getPageNumber(), page.getPageSize() );
        else{
            String prop = sort.iterator().next().getProperty();

            if( !prop.equals( "rating" ) )
                pageRequest = new PageRequest( page.getPageNumber(), page.getPageSize(), sort );
            else
                pageRequest = new PageRequest(
                        page.getPageNumber(),
                        page.getPageSize(),
                        JpaSort.unsafe( Sort.Direction.DESC, "avg( erv )" )
                );
        }

        Page<ExecutorRatingExtended2> executorsRatings = this.executorRatingService.findAll( pageRequest );

        model.addAttribute( "executorsRating", executorsRatings );

        return "ratingExecutors";
    }

    @RequestMapping( value = "rating_customers" )
    public String ratingCustomers(
            Model model,
            Pageable page
    ){
        Sort sort = page.getSort();
        PageRequest pageRequest;

        if( sort == null ) pageRequest = new PageRequest( page.getPageNumber(), page.getPageSize() );
        else{
            String prop = sort.iterator().next().getProperty();

            if( !prop.equals( "rating" ) )
                pageRequest = new PageRequest( page.getPageNumber(), page.getPageSize(), sort );
            else
                pageRequest = new PageRequest(
                        page.getPageNumber(),
                        page.getPageSize(),
                        JpaSort.unsafe( Sort.Direction.DESC, "avg( ucr )" )
                );
        }

        Page<UserExtended> users = this.userService.findAllExtended( pageRequest );

        model.addAttribute( "users", users );

        return "ratingCustomers";
    }
}
