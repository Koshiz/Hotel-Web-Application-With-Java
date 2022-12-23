package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

import dao.hotel_adminDao;
import model.hotel_admin;
import paypal.OrderDetail;
import paypal.PaymentServices;

import com.paypal.base.rest.PayPalRESTException;
import dao.bookingDao;
import dao.customerDao;
import model.customer;
import model.booking;


@WebServlet(name = "NewBooking", value = "/NewBooking")
public class NewBooking extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private bookingDao bookingDao;
    private customerDao customerDao;


    public void init() {
        bookingDao = new bookingDao();
        customerDao = new customerDao();

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
                insertBooking(request, response);
            }else{
                response.sendRedirect("login.html");
            }

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

    }
    private void insertBooking(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException, ServletException {

        Date today = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String check_in = request.getParameter("check_in");//--//
        String check_out = request.getParameter("check_out");//--//
        String date = dateFormat.format(today);
        //String date = "2022-01-05";
        String type = request.getParameter("type");//--//
        String adults = request.getParameter("adults");//--//
        String status = "pending";
        String email = request.getParameter("email");//--//
        String review = "none";







        Date c5 = new SimpleDateFormat("yyyy-MM-dd").parse(check_in);
        Date c7 =new SimpleDateFormat("yyyy-MM-dd").parse(check_out);

        int count = 0;
        List<booking> listBooking = bookingDao.getBookingsoverlap( type , adults , "pending" );

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
            //String username = request.getParameter("username");


            HttpSession session = request.getSession();
            String username = (String)session.getAttribute("customer_email");

            customer customer = customerDao.getCustomer(username);
            String phone=customer.getPhone1();
            session.setAttribute("phone", phone);
            session.setAttribute("check_in", check_in);
            session.setAttribute("check_out", check_out);
            session.setAttribute("date", date);
            session.setAttribute("type", type);
            session.setAttribute("adults", adults);
            session.setAttribute("status", status);
            session.setAttribute("email", email);
            session.setAttribute("review", review);

            String product =type+" "+adults;

            String total = "50";


            OrderDetail orderDetail = new OrderDetail(product, total);
            try {
                PaymentServices paymentServices = new PaymentServices();
                String approvalLink = paymentServices.authorizePayment(orderDetail);

                response.sendRedirect(approvalLink);

            } catch (PayPalRESTException ex) {
                request.setAttribute("errorMessage", ex.getMessage());
                ex.printStackTrace();
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }





        }else{


            String error_message = "no rooms available in the selected dates";
            request.setAttribute("error_message", error_message);
            request.setAttribute("count", count);
            RequestDispatcher dispatcher = request.getRequestDispatcher("make-booking.jsp");
            dispatcher.forward(request, response);

        }



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
