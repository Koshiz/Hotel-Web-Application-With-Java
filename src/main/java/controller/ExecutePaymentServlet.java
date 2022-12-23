package controller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import dao.bookingDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;

import model.booking;
import paypal.OrderDetail;
import paypal.PaymentServices;
import sun.plugin.dom.core.Element;

@WebServlet(name = "ExecutePaymentServlet", value = "/execute_payment")
public class ExecutePaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private bookingDao bookingDao;

    public void init() {
        bookingDao = new bookingDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        try {
            if(check_user(request, response)){

                HttpSession session = request.getSession();




                String check_in = (String) session.getAttribute("check_in");
                String check_out = (String)session.getAttribute("check_out");
                String date =(String) session.getAttribute("date");
                String type = (String)session.getAttribute("type");
                String adults = (String)session.getAttribute("adults");
                String status = (String)session.getAttribute("status");
                String email = (String)session.getAttribute("email");
                String review =(String) session.getAttribute("review");

                String phone =(String) session.getAttribute("phone");
               // String msg = type+" "+adults +" on "+check_in+" to "+check_out;

                String paymentId = request.getParameter("paymentId");
                String payerId = request.getParameter("PayerID");
                PaymentServices paymentServices = new PaymentServices();
                Payment payment = paymentServices.executePayment(paymentId, payerId);

                PayerInfo payerInfo = payment.getPayer().getPayerInfo();
                Transaction transaction = payment.getTransactions().get(0);

                ///////////////////////////////
                booking newBooking = new booking(check_in,check_out,date,type,adults,status,email,review);
                String message ="booking confirmed for "+type+" from "+check_in+" to "+check_out+" .";
                bookingDao.saveBooking(newBooking);
                send("pusl2024group36@gmail.com", "qihirsapnefittwf", email, "booking confirmed", message);
                if(false){
                    try {



                        HttpURLConnection conn = (HttpURLConnection) new URL("https://app.notify.lk/api/v1/send?").openConnection();
                        String data = "user_id=15272&api_key=gaLbIQ9McjflVBP5GKLl&sender_id=NotifyDEMO&to="+phone+"&message="+message;
                        conn.setDoOutput(true);
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                        conn.getOutputStream().write(data.getBytes("UTF-8"));

                        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuffer stringBuffer = new StringBuffer();
                        String line;
                        while ((line = rd.readLine()) != null) {
                            stringBuffer.append(line).append("\n");
                        }
                        System.out.println(stringBuffer.toString());
                        rd.close();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }




                response.sendRedirect("booking-done.jsp");
                //////////////////////////////////////////////




            }else{
                response.sendRedirect("login.html");
            }

        } catch (PayPalRESTException | SQLException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            ex.printStackTrace();
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
    /**
     *
     * @param from sender email address (from which letter will be sent)
     * @param password sender email password
     * @param to recipient email address
     * @param sub subject of the letter
     * @param msg message body
     */
    public static void send(String from, String password, String to, String sub, String msg) {
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");


        //get Session
        Session session2 = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });
        //compose message
        try {
            MimeMessage message = new MimeMessage(session2);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);



            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (Exception ex) {
            Logger.getLogger(NewBooking.class.getName()).log(Level.SEVERE, null, ex);
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
