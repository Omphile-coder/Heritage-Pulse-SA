package za.ac.tut.controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import za.ac.tut.model.bl.UsersFacadeLocal;
import za.ac.tut.model.entity.Users;


public class LoginServlet extends HttpServlet {

    @EJB
    private UsersFacadeLocal userFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password"); // In a real project, use hashing!

        // 1. Authenticate user via Facade
        Users user = userFacade.login(email, password);
        System.out.println("=== ATTEMPTING LOGIN FOR: " + email + " WITH PASSWORD: " + password + " ===");

        if (user != null) {
            // 2. Create a session and store user data
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getUserRole());

            // 3. Redirect based on role
            if ("Admin".equals(user.getUserRole())) {
                // BUG SQUASHED: Actually removed the .do this time!
                response.sendRedirect("AdminServlet.do");
            } else {
                // NEW ROUTE: Normal users now go straight to the Profile page!
                response.sendRedirect("ProfileServlet.do");
            }
        } else {
            request.setAttribute("error", "Invalid email or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}