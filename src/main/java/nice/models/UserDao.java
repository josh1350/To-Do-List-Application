package nice.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

  User findByName(String name);

  User findUserById(Long id);

  void deleteById(Long id);

  List<User> findAll();

  public void deleteAll();

  User findUserByEmail(String email);

  // boolean isUserEmailPresent(String email);

  User findByEmail(String email);

  // void deleteByUserName(String name);

}