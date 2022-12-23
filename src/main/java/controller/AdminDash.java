package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.hotel_adminDao;
import model.hotel_admin;

import dao.bookingDao;
import model.booking;

import dao.reviewsDao;
import model.reviews;

import dao.customerDao;
import model.customer;

@WebServlet(name = "AdminDash", value = "/AdminDash")
public class AdminDash extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private hotel_adminDao hotel_adminDao;

    private bookingDao bookingDao;
    private reviewsDao reviewsDao;
    private customerDao customerDao;

    public void init() {
        hotel_adminDao = new hotel_adminDao();
        bookingDao = new bookingDao();
        reviewsDao = new reviewsDao();
        customerDao = new customerDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if(check_user(request, response)){
                response.sendRedirect("login.html");
            }
            //response.sendRedirect("login.html");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {


            if(check_user(request, response)){

                String username = request.getParameter("username");
                String password = request.getParameter("password");



                hotel_admin Hotel_admin = hotel_adminDao.getUser(username);

                String password_temp=Hotel_admin.getPassword();
                String username_temp=Hotel_admin.getUsername();
                String name_temp=Hotel_admin.getName();

                if (Objects.equals(password, password_temp)) {

                    List<customer> listCustomer = customerDao.getAllCustomer();
                    List<reviews> listReview = reviewsDao.getAllReviews();
                    List<booking> listBooking = bookingDao.getBookingsPending("pending");

                    request.setAttribute("reviewsCount", listReview.size());
                    request.setAttribute("customerCount", listCustomer.size());
                    request.setAttribute("bookingCount", listBooking.size());

                    HttpSession session = request.getSession();
                    session.setAttribute("admin_username", username_temp);
                    session.setAttribute("admin_name", name_temp);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("admin-dash.jsp");
                    dispatcher.forward(request, response);
                }else{
                    response.sendRedirect("login.html");

                }
            }else{
                response.sendRedirect("login.html");
            }




        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




    }


    private boolean check_user(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        boolean flag= true;
        HttpSession session = request.getSession();

        if (session.getAttribute("admin_username") != null ){
            flag=false;

        }else{
            if (session.getAttribute("customer_email") != null ){
                flag=false;
                response.sendRedirect("CustomerDash");
            }
        }
        return flag;


    }

}
