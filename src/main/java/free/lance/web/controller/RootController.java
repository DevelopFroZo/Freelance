package free.lance.web.controller;

import free.lance.domain.response.TaskCard;
import free.lance.domain.model.User;
import free.lance.domain.service.TaskService;
import free.lance.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootController{
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

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
    public String registerGet(){
        return "register";
    }

    @RequestMapping( value = "/register", method = RequestMethod.POST )
    public String register( @ModelAttribute User user ){
        boolean isSaved = this.userService.save( user );

        if( isSaved ){
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    user,
                    user.getPassword(),
                    user.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication( auth );

            return "redirect:/";
        } else {
            // FIXME send an error
            return "register";
        }
    }
}
