package dao;


import org.hibernate.Session;
import org.hibernate.Transaction;

import model.hotel_admin;
import util.HibernateUtil;

import java.util.List;

public class hotel_adminDao {

    /**
     * Get User By ID
     * @param username
     * @return
     */
    public hotel_admin getUser(String username) {

        Transaction transaction = null;
        hotel_admin hotel_admin = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            hotel_admin = session.get(hotel_admin.class, username);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return hotel_admin;
    }



}
