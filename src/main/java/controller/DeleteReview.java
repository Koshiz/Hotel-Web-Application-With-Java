package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

import dao.reviewsDao;
import model.reviews;

import dao.bookingDao;

import model.booking;


@WebServlet(name = "DeleteReview", value = "/DeleteReview")
public class DeleteReview extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private reviewsDao reviewsDao;
    private bookingDao bookingDao;

    public void init() {
        reviewsDao = new reviewsDao();
        bookingDao = new bookingDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            if(check_user(request, response)){
                deleteReview(request, response);
            }else{
                response.sendRedirect("login.html");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if(check_user(request, response)){
                response.sendRedirect("login.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteReview(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        reviewsDao.deleteReview(id);
        booking existingbooking = bookingDao.getBooking(id);
        existingbooking.setReview("0");
        bookingDao.updateBooking(existingbooking);

        response.sendRedirect("AllReviews");
        //redirect to servlet to set attribute for listreview,dont redirect to jsp as attribute no longer is in response
    }
    private boolean check_user  (HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        boolean flag= true;
        HttpSession session = request.getSession();


        if (session.getAttribute("admin_username") == null || session.getAttribute("admin_username").equals("")){
            response.sendRedirect("login.html");
            flag= false;

        }
        if (session.getAttribute("customer_email") != null ){
            response.sendRedirect("CustomerDash");
            flag= false;

        }
        return flag;

    }


}
