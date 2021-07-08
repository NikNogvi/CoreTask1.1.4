package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {

            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS `JMUsers` (\n" +
                    "  `id` bigint NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(45) NOT NULL,\n" +
                    "  `lastName` varchar(45) NOT NULL,\n" +
                    "  `age` tinyint NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE KEY `id_UNIQUE` (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci")
                    .executeUpdate();
            session.getTransaction().commit();

            System.out.println("Table create");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception while create table");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {

            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS `mydbtest`.`JMUsers`;")
                    .executeUpdate();
            session.getTransaction().commit();

            System.out.println("Table drop");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception while drop table");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Session session = Util.getSessionFactory().openSession()) {

            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();

            System.out.println(" User " + name + " add");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception while adding user");
        }
    }

    @Override
    public void removeUserById(long id) {

        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();

            if (session.get(User.class, id) != null) {
                session.delete(session.get(User.class, id));
                System.out.println(" User with " + id + " remove");
            } else {
                System.out.println("User with id = " + id + " does not exist");
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception while remove user");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            userList = session
                    .createQuery("from User")
                    .getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception while get users list");
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table clean");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Table cleanup exception");
        }

    }
}
