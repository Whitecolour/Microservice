package web.dao;

import web.model.User;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface UserDao {
    void createTableUsers();
    void dropTableUsers();
    void saveUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(Long id);

}
