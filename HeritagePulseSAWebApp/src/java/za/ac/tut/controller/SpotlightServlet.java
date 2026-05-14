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
import za.ac.tut.model.entity.ArchiveItems;

@WebServlet(name = "SpotlightServlet", urlPatterns = {"/SpotlightServlet"})
public class SpotlightServlet extends HttpServlet {

    @EJB
    private ArchiveItemsFacadeLocal archiveFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Use a custom query to get only items with media
        List<ArchiveItems> mediaItems = archiveFacade.findMediaRichContent();
        
        request.setAttribute("spotlightItems", mediaItems);
        request.getRequestDispatcher("spotlight.jsp").forward(request, response);
    }
}