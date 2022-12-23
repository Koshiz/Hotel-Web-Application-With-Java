package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

import dao.customerDao;
import model.customer;

@WebServlet(name = "DeleteCustomer", value = "/DeleteCustomer")
public class DeleteCustomer extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private customerDao customerDao;

    public void init() {
        customerDao = new customerDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            if(check_user(request, response)){
                deleteCustomer(request, response);
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

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String email = request.getParameter("email");
        customerDao.deleteCustomer(email);
        response.sendRedirect("AllCustomers");
        //redirect to servlet to set attribute for listreview,dont redirect to jsp as attribute no longer is in response
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
