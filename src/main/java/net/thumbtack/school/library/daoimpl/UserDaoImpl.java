package net.thumbtack.school.library.daoimpl;

import net.thumbtack.school.library.dao.UserDao;
import net.thumbtack.school.library.database.Database;
import net.thumbtack.school.library.model.User;
import net.thumbtack.school.library.service.ServiceException;
import java.util.Map;

// пакет с имплементациями dao-интерфейсов.
public class UserDaoImpl implements UserDao {
    private Database database = Database.getInstance();

    public UserDaoImpl(){}


    @Override
    public String login(String login, String password) throws ServiceException {
        return database.login(login,password);
    }

    @Override
    public String register(User user) throws ServiceException {
        return database.insertUser(user.getLogin(),user);
    }

    @Override
    public String deleteUser(String token) throws ServiceException {
        database.deleteUser(token);
        return null;
    }

    @Override
    public void startServer(Map<String, User> map) {
        database.setMapUser(map);
    }

    @Override
    public Map<String, User> stopServer() {
        return database.getMapUser();
    }

    @Override
    public String logout(String token) throws ServiceException {
        return database.logout(token);
    }

}
