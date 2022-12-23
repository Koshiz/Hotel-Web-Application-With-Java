package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.reviewsDao;

import model.reviews;

@WebServlet(name = "CustomerAllReviews", value = "/CustomerAllReviews")
public class CustomerAllReviews extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private reviewsDao reviewsDao;

    public void init() {
        reviewsDao = new reviewsDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            if(check_user(request, response)){
                listCustomerReviews(request, response);
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
                response.sendRedirect("CustomerLogin.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void listCustomerReviews(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        HttpSession session = request.getSession();
        String email = (String)session.getAttribute("customer_email");

        List<reviews> listReviews = reviewsDao.getReviewsForCustomer(email);
        request.setAttribute("listReviews", listReviews);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer-reviews.jsp");
        dispatcher.forward(request, response);
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
