package za.ac.tut.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.ac.tut.model.bl.ArchiveItemsFacadeLocal;
import za.ac.tut.model.bl.TribesFacadeLocal;
import za.ac.tut.model.entity.ArchiveItems;

/**
 *
 * @author lucas
 */
public class ArchiveServlet extends HttpServlet {

   @EJB
    private TribesFacadeLocal tribeFacade;
   
   @EJB
   private ArchiveItemsFacadeLocal archiveFacade;
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        request.setAttribute("tribes", tribeFacade.findAll());
        
        // 2. Handle filtering if parameters are present
        String tribeId = request.getParameter("tribeId");
        String category = request.getParameter("category");
        List<ArchiveItems> results;
        
       
        if (tribeId != null && !tribeId.isEmpty()) {
            // User chose a specific tribe (e.g., Zulu, Venda)
            Integer tId = Integer.parseInt(tribeId);
            results = archiveFacade.filterByTribe(tId);
        } else if (category != null && !category.isEmpty()) {
            // User chose a specific category (e.g., Attire, Cuisine)
            results = archiveFacade.filterByCategory(category);
        } else {
            // Default view: Show everything in the Living Archive
            results = archiveFacade.findAll(); 
        }
        
        
        request.setAttribute("archiveItems", results);

        RequestDispatcher disp = request.getRequestDispatcher("archive.jsp");
        disp.forward(request, response);
        
    }

    

}
