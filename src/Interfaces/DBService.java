package Interfaces;

import com.google.gson.Gson;
import dataSets.UsersDataSet;
import executor.DBException;
import org.hibernate.SessionFactory;

import javax.security.auth.login.Configuration;

public interface DBService {
    UsersDataSet getUser(long id) throws DBException;
    Boolean haveToken(Integer token);
    String getLoginByToken(Integer token);
    long addUser(String login, String password, Double latitude,Double longitude) throws DBException;
    Boolean auth(String login, String password) throws  DBException;
    String getLocation(String login) throws DBException;
    void printConnectInfo();
}
