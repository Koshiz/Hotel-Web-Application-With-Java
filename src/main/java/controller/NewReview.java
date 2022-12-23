package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;


import dao.reviewsDao;

import model.reviews;
import dao.bookingDao;

import model.booking;


@WebServlet(name = "NewReview", value = "/NewReview")
public class NewReview extends HttpServlet {

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
                response.sendRedirect("CustomerLogin.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            if(check_user(request, response)){
                insertReview(request, response);
            }else{
                response.sendRedirect("login.html");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private void insertReview(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Date today = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        int id = Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("email");
        String title = request.getParameter("title");
        String comment = request.getParameter("comment");
        int score = Integer.parseInt(request.getParameter("score"));
        String date = dateFormat.format(today);

        booking existingbooking = bookingDao.getBooking(id);
        existingbooking.setReview("reviewed");
        bookingDao.updateBooking(existingbooking);
        reviews newReview = new reviews( id, email, title, comment, score, date);

        reviewsDao.saveReview(newReview);
        response.sendRedirect("CustomerAllBookings");

    }

    private boolean check_user  (HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        boolean flag= true;
        HttpSession session = request.getSession();


        if (session.getAttribute("customer_email") == null || session.getAttribute("customer_email").equals("")){
            response.sendRedirect("CustomerLogin.html");
            flag= false;

        }
        if (session.getAttribute("admin_username") != null ){
            response.sendRedirect("AdminDash");
            flag= false;

        }
        return flag;

    }
}
