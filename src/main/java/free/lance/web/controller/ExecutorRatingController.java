package free.lance.web.controller;

import free.lance.domain.response.ExecutorRatingExtended;
import free.lance.domain.service.ExecutorRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
@RequestMapping( value = "/executor_rating" )
public class ExecutorRatingController{
    @Autowired
    private ExecutorRatingService executorRatingService;

    @RequestMapping( value = "get_by_user_ids" )
    @PreAuthorize( "hasRole( 'ROLE_USER' )" )
    @ResponseBody
    public Set<ExecutorRatingExtended> getByUserIds(
            @RequestParam( "user_ids" ) Set<Long> userIds
    ){
        return this.executorRatingService.findAllByUserIds( userIds );
    }
}
