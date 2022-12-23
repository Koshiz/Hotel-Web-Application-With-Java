package dao;


import org.hibernate.Session;
import org.hibernate.Transaction;

import model.booking;
import util.HibernateUtil;

import java.util.List;


public class bookingDao {


    /**
     * Get all bookings
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<booking> getAllBooking() {

        Transaction transaction = null;
        List < booking > listOfBooking = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an booking object

            listOfBooking = session.createQuery("from booking").getResultList();

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
        return listOfBooking;
    }

    /**
     * Delete User
     * @param id
     */
    public void deleteBooking(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a user object
            booking booking = session.get(booking.class, id);
            if (booking != null) {
                session.delete(booking);
                System.out.println("booking is deleted");
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
     * Update User
     * @param booking
     */
    public void updateBooking(booking booking) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the booking object
            session.update(booking);
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
     * @param id
     * @return
     */
    public booking getBooking(int id) {

        Transaction transaction = null;
        booking booking = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            booking = session.get(booking.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return booking;
    }

    /**
     * Save User
     * @param booking
     */
    public void saveBooking(booking booking) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the booking object
            session.save(booking);
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
     * Get all bookings
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<booking> getBookingCustomer(String email) {

        Transaction transaction = null;
        List < booking > listOfBooking = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an booking object

            listOfBooking = session.createQuery("from booking where email ='"+email+"' and status ='done'").getResultList();

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
        return listOfBooking;
    }

    /**
     * Get all bookings
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<booking> getBookingsoverlap(String type,String adults ,String status ) {

        Transaction transaction = null;
        List < booking > listOfBooking = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an booking object

            listOfBooking = session.createQuery("from booking where type ='"+type+"' and adults ='"+adults+"' and status = '" +status+ "'" ).getResultList();

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
        return listOfBooking;
    }

    /**
     * Get all bookings
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<booking> getBookingsPending(String status ) {

        Transaction transaction = null;
        List < booking > listOfBooking = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an booking object

            listOfBooking = session.createQuery("from booking where status ='"+status+"'").getResultList();

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
        return listOfBooking;
    }

}
