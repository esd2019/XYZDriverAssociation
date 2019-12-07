/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import dao.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

import java.util.Calendar;

/**
/**
 *
 * @author jennistly
 */
public class memberRegister extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    // <editor-fold defaultstate="collapsed" desc="Sign Up Action">
        String email = request.getParameter("email").trim();
        String fullname = request.getParameter("fullname").trim();
        String status = "Applied";
        String dob = request.getParameter("dob").trim();
        String street = request.getParameter("street").trim();
        String area = request.getParameter("area").trim();
        String city = request.getParameter("city").trim();
        String county = request.getParameter("county").trim();
        String postcode = request.getParameter("postcode").trim();
        String address = street + "," + area + "," + city + "," + county + "," + postcode;
        int balance = 0;
        String password = request.getParameter("password").trim();
        String account = request.getParameter("account").trim();
        String code = request.getParameter("code").trim();
        String month = request.getParameter("month").trim();
        String year = request.getParameter("year").trim();
        request.setAttribute("fullname", fullname);
        String email_err="err";

        

         if (new UserDAOImpl().checkAccountExist(email)) {
             request.setAttribute("email_err", "email exists");
          

        } else {
            request.setAttribute("email", email);
            email_err = "";
            
        }

        
        request.setAttribute("street", street);
        request.setAttribute("area", area);
        request.setAttribute("city", city);
        request.setAttribute("county", county);
        request.setAttribute("postcode", postcode);
        
        request.setAttribute("password", password);
            
        request.setAttribute("account", account);
            
        request.setAttribute("code", code);
            
        
        request.setAttribute("month", month);
            
        request.setAttribute("year", year);
            
        

        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dor = formatter.format(today);

        String url = "";
        if (!email_err.equals("")) {
                url = "/register.jsp";
                request.setAttribute("email", "email exists");
                
                RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
                rd.forward(request, response);
            } else {

        
            url = "/user/userdashboard.jsp";
            //String encryptedPassword = AES.encrypt(password, secretKey);
            User u = new User(fullname, email, status, Double.valueOf(balance), address, password, Integer.parseInt(account), Integer.parseInt(code), Integer.parseInt(month), Integer.parseInt(year), dob, dor);
            new UserDAOImpl().insertAccount(u);

            HttpSession s = request.getSession();
            s.setAttribute("username", email);
            s.setAttribute("email", email);
            s.setAttribute("success", "SIGN UP SUCCESSFULLY!");
            response.sendRedirect("user/userdashboard.jsp");
        }
        
    
            
    }
    
    
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
