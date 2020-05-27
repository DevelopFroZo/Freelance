package free.lance.domain.service;

import free.lance.domain.model.User;
import free.lance.domain.model.UserRole;
import free.lance.domain.repository.UserRepository;
import free.lance.domain.response.UserExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

    public void incBalance( Long id, Long value ){
        this.userRepository.incBalance( id, value );
    }

    public Page<UserExtended> findAllExtended( Pageable page ){
        return this.userRepository.findAllExtended( page );
    }
}
