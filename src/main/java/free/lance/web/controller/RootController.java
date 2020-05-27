package free.lance.web.controller;

import free.lance.domain.response.ExecutorRatingExtended;
import free.lance.domain.response.ExecutorRatingExtended2;
import free.lance.domain.response.TaskCard;
import free.lance.domain.model.User;
import free.lance.domain.service.ExecutorRatingService;
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
import java.util.Set;

@Controller
public class RootController{
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private ExecutorRatingService executorRatingService;

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
    @PreAuthorize( "hasRole( 'ROLE_USER' )" )
    public String personalAccount(
            Model model,
            Authentication authentication
    ){
        Set<Long> userIds = new HashSet<>();
        User current = (User) authentication.getPrincipal();

        userIds.add( current.getId() );
        Set<ExecutorRatingExtended> executorsRatingsExtended = this.executorRatingService.findAllByUserIds( userIds );

        model.addAttribute( "user", current );
        model.addAttribute( "executorRating", executorsRatingsExtended );

        return "personalAccount";
    }

    @RequestMapping( value = "rating_executors" )
    public String ratingExecutors(
            Model model,
            Pageable page
    ){
        Page<ExecutorRatingExtended2> executorsRatings = this.executorRatingService.findAll( page );

        model.addAttribute( "executorsRating", executorsRatings );

        return "ratingExecutors";
    }
}
