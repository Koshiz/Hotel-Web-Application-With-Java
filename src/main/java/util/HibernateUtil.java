package util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import model.hotel_admin;
import model.customer;
import model.booking;
import model.reviews;

/**
 * Java based configuration
 *
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                //settings.put(Environment.URL, "jdbc:mysql://localhost:3306/demo?useSSL=false");

                //settings.put(Environment.URL, "jdbc:mysql://localhost:3306/demo?useSSL= FALSE&useUnicode=TRUE&useJDBCCompliantTimezoneShift=TRUE&useLegacyDatetimeCode=FALSE&serverTimezone=UTC");


                //settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hotel?useSSL= FALSE&useJDBCCompliantTimezoneShift=TRUE&useLegacyDatetimeCode=FALSE&serverTimezone=UTC");
                //settings.put(Environment.USER, "root");
                //settings.put(Environment.PASS, "");

                settings.put(Environment.URL, "jdbc:mysql://pusl2024.c4zxdqfin0lj.us-east-2.rds.amazonaws.com:3306/test1h?useSSL= FALSE&useJDBCCompliantTimezoneShift=TRUE&useLegacyDatetimeCode=FALSE&serverTimezone=UTC");
                settings.put(Environment.USER, "admin");
                settings.put(Environment.PASS, "admin12345");





                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                //keep as update, not "drop and create" to avoid new table every run
                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(hotel_admin.class);
                configuration.addAnnotatedClass(customer.class);
                configuration.addAnnotatedClass(booking.class);
                configuration.addAnnotatedClass(reviews.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                System.out.println("Hibernate Java Config serviceRegistry created");
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                return sessionFactory;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
