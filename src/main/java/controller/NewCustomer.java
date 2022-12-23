package controller;

import dao.customerDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;


import dao.customerDao;
import model.customer;

@WebServlet(name = "NewCustomer", value = "/NewCustomer")
public class NewCustomer extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private dao.customerDao customerDao;

    public void init() {
        customerDao = new customerDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            insertCustomer(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private void insertCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String phone1 = request.getParameter("phone1");
        String phone2 = request.getParameter("phone2");
        String address = request.getParameter("address");
        customer newCustomer = new customer(email,password,first_name,last_name,phone1,phone2,address);
        customerDao.saveCustomer(newCustomer);
        response.sendRedirect("CustomerLogin.html");
    }

}
