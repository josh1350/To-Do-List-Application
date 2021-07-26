package nice.services;

import nice.models.UserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import nice.models.User;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private UserDao userDao;

	@InjectMocks
	private UserService userService = new UserService(userDao, null, null,null);

	private Long testUserId = 1l;
	private String testUserName = "testUserName1";
	private User testUser;

	@Before
	public void setup() {
		
		MockitoAnnotations.initMocks(this);
		testUser = new User();
		testUser.setName(testUserName);
		testUser.setId(testUserId);
	}

	@Test
	public void exampleTest() {
		assertEquals(1, 1);
	}

	@Test
	public void createUserTest() {
		Mockito.when(userDao.save(Mockito.any(User.class))).thenReturn(testUser);

		User user = userService.createUser(testUser);

		Mockito.verify(userDao, Mockito.times(1)).save(Mockito.any(User.class));
		assertNotNull(user);
		assertNotNull(user.getId());
		assertEquals(testUserName, user.getName());
	}

	@Test
	public void updateUserTest() {
		String updatedUserName = ("updatedUserName");
		User updatedUser = new User();
		updatedUser.setId(testUserId);
		updatedUser.setName(updatedUserName);

		Mockito.when(userDao.findUserById(Mockito.anyLong())).thenReturn(testUser);
		Mockito.when(userDao.save(updatedUser)).thenReturn(updatedUser);

		User result = userService.updateUser(testUserId, updatedUserName);

		Mockito.verify(userDao, Mockito.times(1)).save(Mockito.any(User.class));
		Mockito.verify(userDao, Mockito.atLeastOnce()).findById(Mockito.anyLong());

		assertNotNull(result);
		assertEquals(testUserId, result.getId());
		assertEquals(updatedUserName, result.getName());
	}

	@After
	public void afterTest(){
		setup();
	}
}