package za.ac.tut.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Fetch the current session (passing 'false' means it won't create a new one if it's already null)
        HttpSession session = request.getSession(false);
        
        // 2. If the user is logged in, destroy their session
        if (session != null) {
            session.invalidate(); 
        }
        
        // 3. Redirect them back to the login page
        response.sendRedirect("index.html");
    }
}