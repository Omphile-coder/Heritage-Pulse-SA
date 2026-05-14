package za.ac.tut.controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import za.ac.tut.model.bl.ArchiveItemsFacadeLocal;
import za.ac.tut.model.entity.ArchiveItems;


public class AdminServlet extends HttpServlet {

    @EJB
    private ArchiveItemsFacadeLocal archiveFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || !"Admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        // Fetch only items awaiting verification
        List<ArchiveItems> pendingItems = archiveFacade.findPendingSubmissions();
        request.setAttribute("pendingItems", pendingItems);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || !"Admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        String itemIdStr = request.getParameter("itemId");
        String action = request.getParameter("action"); // 'Approve' or 'Reject'

        if (itemIdStr != null) {
            Integer id = Integer.parseInt(itemIdStr);
            ArchiveItems item = archiveFacade.find(id);

            if (item != null) {
                if ("Approve".equals(action)) {
                    item.setStatus("Approved");
                    archiveFacade.edit(item);
                    // Add success message
                    session.setAttribute("adminMessage", "Item '" + item.getTitle() + "' was successfully approved and added to the Archive.");
                } else if ("Reject".equals(action)) {
                    archiveFacade.remove(item);
                    // Optional: You could add Java File I/O logic here to physically delete the media file from your hard drive
                    // Add rejection message
                    session.setAttribute("adminMessage", "Item '" + item.getTitle() + "' was rejected and removed from the system.");
                }
            }
        }
      
        response.sendRedirect("AdminServlet.do");
    }
}