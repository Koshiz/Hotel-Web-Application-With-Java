package dao;

import model.booking;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.reviews;
import util.HibernateUtil;

import java.util.List;


public class reviewsDao {
    /**
     * Get all reviews
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<reviews> getAllReviews() {

        Transaction transaction = null;
        List < reviews > listOfReviews = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an review object

            listOfReviews = session.createQuery("from reviews ").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfReviews;
    }

    /**
     * Delete User
     * @param id
     */
    public void deleteReview(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a reviews object
            reviews reviews = session.get(reviews.class, id);
            if (reviews != null) {
                session.delete(reviews);
                System.out.println("review is deleted");
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
     * @param reviews
     */
    public void saveReview(reviews reviews) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the reviews object
            session.save(reviews);
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
     * Get all reviews
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<reviews> getReviewsForCustomer(String email) {

        Transaction transaction = null;
        List < reviews > listOfReviews = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an review object

            listOfReviews = session.createQuery("from reviews where email ='"+email+ "'" ).getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfReviews;
    }

}
