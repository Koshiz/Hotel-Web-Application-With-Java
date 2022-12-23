package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

import dao.bookingDao;

import model.booking;

@WebServlet(name = "DeleteBooking", value = "/DeleteBooking")
public class DeleteBooking extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private bookingDao bookingDao;

    public void init() {
        bookingDao = new bookingDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            if(check_user(request, response)){
                deleteBooking(request, response);
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


    private void deleteBooking(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        bookingDao.deleteBooking(id);
        response.sendRedirect("admin-booking-deleted.jsp");
        //redirect to jsp
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
