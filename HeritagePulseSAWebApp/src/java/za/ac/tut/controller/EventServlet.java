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
import za.ac.tut.model.bl.EventsFacadeLocal;
import za.ac.tut.model.entity.Events;


public class EventServlet extends HttpServlet {

    @EJB
    private EventsFacadeLocal eventFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Capture user GPS from the request (sent via JavaScript)
        String latParam = request.getParameter("lat");
        String lngParam = request.getParameter("lng");

        List<Events> eventsToShow;

        if (latParam != null && !latParam.isEmpty() && lngParam != null && !lngParam.isEmpty()) {
            // User clicked "Refresh Pulse" / "Detect Location"
            double lat = Double.parseDouble(latParam);
            double lng = Double.parseDouble(lngParam);
            
            // Fetch events within a 10km radius
            eventsToShow = eventFacade.findNearbyEvents(lat, lng, 10.0);
            request.setAttribute("isLocationActive", true); // A flag to tell the JSP the user is localized
        } else {
            // THE FIX: User just opened the page. Show ALL active events!
            eventsToShow = eventFacade.findAll(); 
            request.setAttribute("isLocationActive", false);
        }

        // Send the list to the map
        request.setAttribute("nearbyEvents", eventsToShow);

        RequestDispatcher disp = request.getRequestDispatcher("map.jsp");
        disp.forward(request, response);
    }
}