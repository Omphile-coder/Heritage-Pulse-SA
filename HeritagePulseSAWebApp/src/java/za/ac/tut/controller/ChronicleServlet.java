package za.ac.tut.controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.ac.tut.model.bl.ArchiveItemsFacadeLocal;

import za.ac.tut.model.bl.TribesFacadeLocal;
import za.ac.tut.model.entity.ArchiveItems;

@WebServlet("/ChronicleServlet")
public class ChronicleServlet extends HttpServlet {

    @EJB
    private ArchiveItemsFacadeLocal archiveFacade;
    
    @EJB
    private TribesFacadeLocal tribeFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Populate the Tribes dropdown
        request.setAttribute("tribes", tribeFacade.findAll());
        
        // 2. Capture Search Parameters
        String keyword = request.getParameter("keyword");
        String tribeIdStr = request.getParameter("tribeId");
        
        List<ArchiveItems> stories;

        // 3. Routing Logic
        if ((keyword != null && !keyword.trim().isEmpty()) || (tribeIdStr != null && !tribeIdStr.isEmpty())) {
            // User is searching
            Integer tribeId = (tribeIdStr != null && !tribeIdStr.isEmpty()) ? Integer.parseInt(tribeIdStr) : null;
            stories = archiveFacade.searchChronicles(keyword, tribeId);
        } else {
            // Default view: Show all stories
            stories = archiveFacade.findApprovedStories();
        }
        
        request.setAttribute("writtenStories", stories);
        request.getRequestDispatcher("chronicles.jsp").forward(request, response);
    }
}