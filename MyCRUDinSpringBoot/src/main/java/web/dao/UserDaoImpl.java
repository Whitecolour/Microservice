package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import jakarta.persistence.EntityManager;

import jakarta.persistence.PersistenceContext;
// Непонятно как работают аннотации
import java.util.List;
@Repository
public class UserDaoImpl implements UserDao {
    private static final String SQL_CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS users ("
            + "id INTEGER PRIMARY KEY AUTO_INCREMENT, "
            + "name VARCHAR(255) NOT NULL, "
            + "email VARCHAR(255) NOT NULL " +
            ")";
    private static final String SQL_DROP_TABLE_USERS = "DROP TABLE IF EXISTS users";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createTableUsers() {

        entityManager.createNativeQuery(SQL_CREATE_TABLE_USERS).executeUpdate();

    }

    @Override
    public void dropTableUsers() {

        entityManager.createNativeQuery(SQL_DROP_TABLE_USERS).executeUpdate();

    }




    //-----------------------------------------------------------------------------------------------------------
    @Transactional
    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(Long id) {
         return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
    @Transactional
    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
    @Transactional
    @Override
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }


}
