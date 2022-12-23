package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.bookingDao;
import model.booking;

@WebServlet(name = "AllBookings", value = "/AllBookings")
public class AllBookings extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private bookingDao bookingDao;

    public void init() {
        bookingDao = new bookingDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            if(check_user(request, response)){
                listBooking(request, response);
            }else{
                //response.sendRedirect("login.html");
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

    private void listBooking(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        List<booking> listBooking = bookingDao.getAllBooking();

        request.setAttribute("listBooking", listBooking);
        RequestDispatcher dispatcher = request.getRequestDispatcher("all-booking.jsp");
        dispatcher.forward(request, response);
    }

    private boolean check_user  (HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        boolean flag= true;
        HttpSession session = request.getSession();

        if ((session.getAttribute("admin_username") == null || session.getAttribute("admin_username").equals(""))
        &&(session.getAttribute("customer_email") == null )){
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
