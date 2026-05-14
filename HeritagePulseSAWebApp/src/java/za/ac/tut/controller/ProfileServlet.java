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


public class ProfileServlet extends HttpServlet {

    @EJB
    private UsersFacadeLocal userFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        // Forward to the profile page
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            
            // 1. Get the current logged-in user
            Users currentUser = (Users) session.getAttribute("user");
            
            // 2. Capture form data (e.g., updating their bio or tribe interest)
            String newInterest = request.getParameter("heritageInterest");
            
            // 3. Update the entity
            currentUser.setHeritageInterest(newInterest); // Assuming this field exists in your Users entity
            
            // 4. Save to database
            userFacade.edit(currentUser);
            
            // 5. Update the session so the UI reflects the change immediately
            session.setAttribute("user", currentUser);
            
            request.setAttribute("successMessage", "Your heritage preferences have been updated!");
        }
        
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
}