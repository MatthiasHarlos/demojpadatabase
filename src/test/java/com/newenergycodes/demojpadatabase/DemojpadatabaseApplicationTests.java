package com.newenergycodes.demojpadatabase;

import com.newenergycodes.demojpadatabase.domain.Attendance;
import com.newenergycodes.demojpadatabase.domain.User;
import com.newenergycodes.demojpadatabase.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DemojpadatabaseApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	void contextLoads() {
	}

	@Test
	@Transactional
	public void testUserEntity(){
		User admin = new User();
		admin.setUsername("admin");
		admin.setPasswordEncoded("abcdef");

		entityManager.persist(admin);

		assertNotNull(admin.getId());
		Assertions.assertTrue(admin.getId()>0);

		User adminFromDb = entityManager.find(User.class, admin.getId());

		assertEquals("admin", adminFromDb.getUsername());
		assertEquals("abcdef", adminFromDb.getPasswordEncoded());

		admin.setPasswordEncoded("Passwort1!");
		entityManager.persist(admin);

		adminFromDb = entityManager.find(User.class, admin.getId());

		assertEquals("admin", adminFromDb.getUsername());
		assertEquals("Passwort1!", adminFromDb.getPasswordEncoded());

		entityManager.remove(admin);

		adminFromDb = entityManager.find(User.class, adminFromDb.getId());

		Assertions.assertNull(adminFromDb);

	}

	@Test
	@Transactional
	public void testAttendanceEntity(){
		User user = new User();
		user.setUsername("max.muster");
		user.setPasswordEncoded("lala");

		entityManager.persist(user);
		assertNotNull(user.getId());

		Attendance attendance = new Attendance();
		attendance.setDate(LocalDateTime.now());
		attendance.setUser(user);

		entityManager.persist(attendance);
		assertNotNull(attendance.getId());

		Attendance attendanceFromDB = entityManager.find(Attendance.class, attendance.getId());
		assertNotNull(attendanceFromDB);
		assertNotNull(attendanceFromDB.getUser());
		assertEquals("max.muster", attendanceFromDB.getUser().getUsername());

		entityManager.refresh(user);
		assertEquals(1, user.getAttendances().size());

		entityManager.clear();
		User maxFromDB = entityManager.find(User.class, user.getId());
		assertEquals(1, maxFromDB.getAttendances().size());
	}

	@Test
	public void testUserRepo(){
		User user = new User();
		user.setUsername("name");
		user.setPasswordEncoded("password");

		user = userRepository.save(user);
		assertNotNull(user.getId());
		Assertions.assertTrue(user.getId()>0);

		Optional<User> userFromDB = userRepository.findById(user.getId());
		assertTrue(userFromDB.isPresent());
		assertEquals("name", userFromDB.get().getUsername());

		Optional<User> max = userRepository.findByUsernameIs("max");
		assertFalse(max.isPresent());

		List<User> userlist = userRepository.findByUsernameStartingWith("nam");
		assertEquals(1, userlist.size());

		List<User> userWithoutAttendance = userRepository.findByAttendancesIsEmpty();

		Optional<User> brigitte = userRepository.findByUsernameStartingWithAndPasswordEncodedEqualsIgnoreCaseAndAttendancesIsNotEmpty("nam", "Password");
		assertNotNull(brigitte);
	    List <User> test = userRepository.getUsersWithoutAttendances();
	}
}
