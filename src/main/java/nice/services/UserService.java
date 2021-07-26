package nice.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nice.models.Role;
import nice.models.RoleDao;
import nice.models.TaskDao;
import nice.models.User;
import nice.models.UserDao;

@Service
public class UserService {

    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";
    private UserDao userDao;
    private RoleDao roleDao;
    @Autowired
    @Qualifier("taskDao")
    private TaskDao taskDoa;
    private BCryptPasswordEncoder cryptPasswordEncoder;

    @Autowired
    public UserService(UserDao userRepository, TaskDao taskRepository, RoleDao roleRepository, BCryptPasswordEncoder cryptPasswordEncoder ) {
        this.userDao = userRepository;
        this.roleDao = roleRepository;
        this.taskDoa = taskRepository;
        this.cryptPasswordEncoder = cryptPasswordEncoder;
        
    }

    // //@Transactional
    // public List<User> findAll() {
    // List<User> users = userDao.findAll();

    // if (CollectionUtils.isNotEmpty(Collections.singleton(users))) {
    // return users;

    // }
    // return users;
    // }

    // //@Transactional
    // public User createUser(User admin) {
    // User user = new User(admin);
    // return userDao.save(user);
    // }

    @Transactional
    public User updateUser(Long id, String userName) {
    try {
    User user = new User();
    user.setName("Josh");
    user.setId(id);
    user.setEmail("fake@yahoo.com");
    user.setPhoto("photo");
    userDao.save(user);
    return user;
    } catch (Exception e) {
    return null;
    }
    }

    // //@Transactional
    // public User deleteUser(String userName) {
    // try {
    // return userDao.deleteById(userName);
    // } catch (Exception e) {
    // return null;
    // }
    // }

    // // @Transactional
    // public User findById(Long id) {
    // try {
    // User user = userDao.findUserById(id);
    // return user;
    // } catch (Exception e) {
    // return null;
    // }
    // }

    // //@Transactional
    // public void deleteAllUsers() {
    // try{
    // userDao.deleteAll();
    // }catch(Exception e){
    // Logger.printThrowable(e);
    // }

    // }

    // @Transactional
    // public User findByIdOrUserName(String idOrUserName) {
    // User user;

    // try {
    // Long id = Long.valueOf(idOrUserName);
    // user = userDao.findUserById(id);
    // } catch (Exception e) {
    // user = null;
    // }

    // if (user == null) {
    // user = userDao.findByUserName(idOrUserName);
    // }

    // return user;
    // 

    public User changeRoleToAdmin(User user) {
        Role adminRole = roleDao.findByRole(ADMIN);
        user.setRoles(new ArrayList<>(Collections.singletonList(adminRole)));
        return userDao.save(user);
    }

    @Transactional
    public List<User> findAll() {
        return userDao.findAll();
    }

    //@Transactional
    public User getUserByEmail(String email){
        return userDao.findByEmail(email);
    }

    @Transactional
    public boolean isUserEmailPresent(String email) {
        return userDao.findByEmail(email) != null;
    }

    //@Transactional
    public User getUserById(Long id) {
        return userDao.findById(id).orElse(null);
    }

    //@Transactional
    public void deleteUser(Long id) {
        User user = userDao.getById(id);
        user.getTasksOwned().forEach(task -> task.setOwner(null));
        userDao.delete(user);
    }

    public Object findByIdOrUserName(String idOrUserName) {
        return null;
    }
    // @Transactional
    // public User createUser( User user2) {
    //     User user = new User();
    //     Long id = Long.MIN_VALUE;
    //     user.setId(id);
    //     user.setName("Josh");
    //     user.setEmail("blah");
    //     String photo = ("photo1");
    //     user.setPhoto(photo);
    //     user.setPassword("password");
    //     return userDao.save(user);
         
    // }

    public User createUser(User user) {
        user.setPassword(cryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleDao.findByRole(USER);
        user.setRoles(new ArrayList<>(Collections.singletonList(userRole)));
        return userDao.save(user);
    }


    // public static void main(String[] args) {

    // String userName = ("Josh");
    // User user = new User(userName);
    // Long id = Long.valueOf(50);
    // user.setId(id);
    // String string1 = user.toString();
    // console().printf(string1,user);

    // }

}