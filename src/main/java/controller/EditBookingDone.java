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
import java.util.List;

import dao.bookingDao;

import model.booking;

@WebServlet(name = "EditBookingDone", value = "/EditBookingDone")
public class EditBookingDone extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private bookingDao bookingDao;

    public void init() {
        bookingDao = new bookingDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if(check_user(request, response)){
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
                updateBooking(request, response);
            }else{
                response.sendRedirect("login.html");
            }

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

    }


    private void updateBooking(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException, ServletException {
        Date today = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        int id = Integer.parseInt(request.getParameter("id"));
        String check_in = request.getParameter("check_in");
        String check_out = request.getParameter("check_out");
        String date = request.getParameter("date");
        String type = request.getParameter("type");
        String adults = request.getParameter("adults");
        String status = request.getParameter("status");
        String email = request.getParameter("email");
        String review = request.getParameter("review");

        Date c5 = new SimpleDateFormat("yyyy-MM-dd").parse(check_in);
        Date c7 =new SimpleDateFormat("yyyy-MM-dd").parse(check_out);

        int count = 0;
        List<booking> listBooking = bookingDao.getBookingsoverlap( type , adults ,"pending" );

        for (booking booking:listBooking){
            String ci_string = booking.getCheck_in();
            String co_string = booking.getCheck_out() ;


            Date ci = new SimpleDateFormat("yyyy-MM-dd").parse(ci_string);
            Date co =new SimpleDateFormat("yyyy-MM-dd").parse(co_string);
            //(     ci<5 && co<=5 )    ||  (    ci >= 7 && co >7)
            //ci.before(c5) ------------- ci<5
            //co.before(c5)  ||  co.equals(c5)  --------------- co<=5
            // ci.after(c7)  ||  ci.equals(c7) ------------- ci >= 7
            // co.after(c7)------------- co >7
            // (    (ci.before(c5)) && (co.before(c5)  ||  co.equals(c5))    )    ||  (    (ci.after(c7)  ||  ci.equals(c7)) && (co.after(c7)) )
            //      (     ci<5 && co<=5 )    ||  (    ci >= 7 && co >7)
            // ( c5.before(co) ||  c5.equals(co) ) --------------  5<co  5<=co
            // ( ci.before(c8) ||  ci.equals(c8) ) --------------  ci < 8  ci = 8
            //
            //5<=co && ci <= 8
            //( c5.before(co) ||  c5.equals(co) ) && ( ci.before(c7) ||  ci.equals(c7) )
            if( c5.before(co)  &&  ci.before(c7)) {
                count++;
            }
        }
        if( count<3) {
            booking booking = new booking( id ,check_in ,  check_out ,  date ,  type ,adults,  status ,  email ,  review );
            bookingDao.updateBooking(booking);
            response.sendRedirect("AllBookings");

        }else{


            String error_message = "no rooms available in the selected dates";
            request.setAttribute("error_message", error_message);
            request.setAttribute("count", count);
            RequestDispatcher dispatcher = request.getRequestDispatcher("edit-booking.jsp");
            dispatcher.forward(request, response);

        }



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
