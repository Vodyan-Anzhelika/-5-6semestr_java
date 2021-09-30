package net.thumbtack.school.library.dao;
import net.thumbtack.school.library.model.User;
import net.thumbtack.school.library.service.ServiceException;

import java.util.Map;

//пакет . Связь с базой данных (классом Database) осуществляется именно dao классами
public interface UserDao {
    String login(String login, String password) throws ServiceException;

    String register(User user) throws ServiceException;

    String deleteUser(String token) throws ServiceException;

    void startServer(Map<String ,User> map);

    Map<String,User> stopServer();

    String logout(String token) throws ServiceException;
   }
//Например:
//
//Из класса DTO мы создали экземпляр класса User, и теперь хотим его добавить в базу данных.
//
//interface UserDao {
//	void insert(User user);
//}
//
//class UserDaoImpl implements UserDao {
//	void insert(User user) {
//// здесь код добавления в базу данных
//}
//
//Разумеется, тип void в качестве типа результата метода здесь выбран лишь для примера, вполне возможно,
// что нужно будет использовать другой тип.