package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.stqa.pft.mantis.model.User;

import java.util.List;

public class DbHelper extends HelperBase {

    public DbHelper(ApplicationManager app) {
        super(app);
    }

    private final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    public List<User> getUsers() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> users = session.createQuery( "from User" ).list();
        session.getTransaction().commit();
        session.close();

        return users;
    }
}
