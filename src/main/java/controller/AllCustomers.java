package controller;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import dao.customerDao;
import model.customer;

//@WebServlet("/")
@WebServlet(name = "AllCustomers", value = "/AllCustomers")
public class AllCustomers extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private customerDao customerDao;

    public void init() {
        customerDao = new customerDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            if(check_user(request, response)){
                listCustomer(request, response);
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

    private void listCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<customer> listCustomer = customerDao.getAllCustomer();
        request.setAttribute("listCustomer", listCustomer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("all-customer.jsp");
        dispatcher.forward(request, response);
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
