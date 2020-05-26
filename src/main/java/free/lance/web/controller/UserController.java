package free.lance.web.controller;

import free.lance.domain.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping( value = "/users" )
public class UserController{
    @RequestMapping( value = "current" )
    @ResponseBody
    public User getCurrent( Authentication authentication ){
        User current = (User) authentication.getPrincipal();

        return current;
    }
}
