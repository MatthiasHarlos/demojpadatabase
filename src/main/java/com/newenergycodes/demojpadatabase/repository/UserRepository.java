package com.newenergycodes.demojpadatabase.repository;

import com.newenergycodes.demojpadatabase.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsernameIs(String username);

    List<User> findByUsernameStartingWith(String username);

    List<User> findByAttendancesIsEmpty();

    Optional<User> findByUsernameStartingWithAndPasswordEncodedEqualsIgnoreCaseAndAttendancesIsNotEmpty(String nam, String password);

    @Query(value = "SELECT u FROM User u WHERE NOT EXISTS (SELECT a FROM Attendance a WHERE a.user = u)", nativeQuery = false)
    List<User> getUsersWithoutAttendances();

 /*
   @Query(value = "SELECT * FROM user WHERE user.id = ...", nativeQuery = true)
   List<User> complexQuery();
 */

}
