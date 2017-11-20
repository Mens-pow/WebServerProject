package dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dataSets.UsersDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public Object getUserId(String login) throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        return ((UsersDataSet)criteria.add(Restrictions.eq("login", login)).uniqueResult());
    }

    public String getUserPass(String login) throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        return ((UsersDataSet)criteria.add(Restrictions.eq("login", login)).uniqueResult()).getPassword();
    }

    public UsersDataSet getUser(String login) throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        return ((UsersDataSet)criteria.add(Restrictions.eq("login", login)).uniqueResult());
    }

    public String getLocation(String login) throws HibernateException{
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        StringBuilder s = new StringBuilder();
        s.append(((UsersDataSet)criteria.add(Restrictions.eq("login", login)).uniqueResult()).getLatitude());
        s.append(" ");
        s.append(((UsersDataSet)criteria.add(Restrictions.eq("login", login)).uniqueResult()).getLongitude());
        Gson gson = new Gson();
        return   gson.toJson(s.toString());
    }


    public long insertUser(String login, String password, Double latitude, Double longitude) throws HibernateException {
       if (getUserId(login) == null) return (Long) session.save(new UsersDataSet(login, password, latitude, longitude));
       else return 0;
    }
}
