package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.customer;
import util.HibernateUtil;

import java.util.List;


public class customerDao {


    /**
     * Get all customers
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<customer> getAllCustomer() {

        Transaction transaction = null;
        List < customer > listOfCustomer = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an customer object

            listOfCustomer = session.createQuery("from customer ").getResultList();

            // commit transaction
            transaction.commit();
            //session.flush();
            //session.clear();
            //session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfCustomer;
    }


    /**
     * Delete User
     * @param email
     */
    public void deleteCustomer(String email) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a customer object
            customer customer = session.get(customer.class, email);
            if (customer != null) {
                session.delete(customer);
                System.out.println("customer is deleted");
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    /**
     * Save User
     * @param customer
     */
    public void saveCustomer(customer customer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the customer object
            session.save(customer);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Get User By ID
     * @param email
     * @return
     */
    public customer getCustomer(String email) {

        Transaction transaction = null;
        customer customer = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an customer object
            customer = session.get(customer.class, email);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return customer;
    }

}
