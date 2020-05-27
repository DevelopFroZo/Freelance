package free.lance.web.controller;

import free.lance.domain.model.User;
import free.lance.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping( value = "/users" )
public class UserController{
    @Autowired
    private UserService userService;

    @RequestMapping( value = "current" )
    @ResponseBody
    public User getCurrent( Authentication authentication ){
        if( authentication == null )
            return null;

        return (User) authentication.getPrincipal();
    }

    @RequestMapping( value = "inc_balance" )
    @ResponseBody
    public String incBalance(
            @RequestParam( "value" ) Long value,
            Authentication authentication
    ){
        if( value == null || value < 1 )
            return "{ \"ok\": false, \"message\": \"Invalid balance\" }";

        User current = (User) authentication.getPrincipal();

        this.userService.incBalance( current.getId(), value );
        current.setBalance( current.getBalance() + value );

        return "{ \"ok\": true }";
    }
}
