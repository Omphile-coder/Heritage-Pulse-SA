package za.ac.tut.controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.ac.tut.model.bl.RemediesFacadeLocal;
import za.ac.tut.model.entity.Remedies;

@WebServlet(name = "ApothecaryServlet", urlPatterns = {"/ApothecaryServlet"})
public class ApothecaryServlet extends HttpServlet {

   @EJB
   private RemediesFacadeLocal rfl;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String searchTerm = request.getParameter("remedySearch");
        
        
        List<Remedies> results = rfl.searchByIndigenousName(searchTerm);
        
        // 3. Set the results as an attribute to be displayed on the JSP/HTML
        request.setAttribute("remedyResults", results);
        
        // 4. Forward back to the search page
        RequestDispatcher disp = request.getRequestDispatcher("apothecary.jsp");
        disp.forward(request, response);
    }
}