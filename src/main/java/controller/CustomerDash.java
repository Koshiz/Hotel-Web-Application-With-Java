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

import dao.customerDao;
import model.customer;

@WebServlet(name = "CustomerDash", value = "/CustomerDash")
public class CustomerDash extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private customerDao customerDao;

    public void init() {
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
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                customer Customer = customerDao.getCustomer(email);
                String password_temp=Customer.getPassword();
                String email_temp=Customer.getEmail();
                String name_temp=Customer.getFirst_name();

                if (Objects.equals(password, password_temp)) {


                    HttpSession session = request.getSession();
                    session.setAttribute("customer_email", email_temp);
                    session.setAttribute("customer_name", name_temp);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("customer-dash.jsp");
                    dispatcher.forward(request, response);
                }else{
                    response.sendRedirect("CustomerLogin.html");

                }

            }else{
                response.sendRedirect("CustomerLogin.html");

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

        if (session.getAttribute("customer_email") != null ){
            flag=false;
            RequestDispatcher dispatcher = request.getRequestDispatcher("customer-dash.jsp");
            dispatcher.forward(request, response);
        }else{
            if (session.getAttribute("admin_username") != null ){
                flag=false;
                response.sendRedirect("AdminDash");
            }
        }

        return flag;

    }

}
