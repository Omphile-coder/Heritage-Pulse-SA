package za.ac.tut.controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.ac.tut.model.bl.UsersFacadeLocal;
import za.ac.tut.model.entity.Tribes;
import za.ac.tut.model.entity.Users;


public class RegisterServlet extends HttpServlet {

    @EJB
    private UsersFacadeLocal usersFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // 1. Grab data from the JSP form
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Integer tribeId = Integer.parseInt(request.getParameter("tribeId"));

            // 2. Create the new User entity
            Users newUser = new Users();
            
            // Generate a random 5-digit ID to prevent database crashes
            int generatedId = (int) (Math.random() * 90000) + 10000;
            newUser.setUserId(generatedId); // Check if your Entity uses setUserId or setId
            
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPassword(password); // Note: In a real-world app, you would hash this!
            newUser.setUserRole("User"); // Automatically make them a standard user
            
            Tribes preferredTribe = new Tribes(tribeId);
            newUser.setFollowedTribeId(preferredTribe);

            // 3. Save to database
            usersFacade.create(newUser);

            // 4. Send them to the login page with a success flag
            response.sendRedirect("login.jsp?success=registered");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=true");
        }
    }
}