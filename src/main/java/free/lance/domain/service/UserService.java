package free.lance.domain.service;

import free.lance.domain.model.ExecutorRating;
import free.lance.domain.model.User;
import free.lance.domain.model.UserRole;
import free.lance.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    public boolean save( User user ){
        user.setRole( UserRole.ROLE_USER );
        user.setBalance( 0L );
        user.setRegisteredAt( LocalDate.now() );

        try{
            this.userRepository.save( user );

            return true;
        }
        catch( Exception e ){
            return false;
        }
    }

    public void decBalance( Long value, Long id ){
        this.userRepository.decBalance( value, id );
    }
}
