package za.ac.tut.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.ac.tut.model.bl.EventsFacadeLocal;
import za.ac.tut.model.entity.Events;
import za.ac.tut.model.entity.Tribes;

public class AddEventServlet extends HttpServlet {

    @EJB
    private EventsFacadeLocal eventsFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // 1. Get Parameters
            String title = request.getParameter("eventTitle");
            Double lat = Double.parseDouble(request.getParameter("latitude"));
            Double lon = Double.parseDouble(request.getParameter("longitude"));
            
            // Convert HTML datetime-local string to SQL Timestamp
            String dateStr = request.getParameter("eventDate").replace("T", " ") + ":00";
            Timestamp eventDate = Timestamp.valueOf(dateStr);
            
            Integer tribeId = Integer.parseInt(request.getParameter("tribeId"));

            // 2. Create Entity
            Events newEvent = new Events();
            newEvent.setEventTitle(title);
            newEvent.setLatitude(lat);
            newEvent.setLongitude(lon);
            newEvent.setEventDate(eventDate);
            
            // Set the relationship (ensure the tribe exists in your DB)
            newEvent.setTribeId(new Tribes(tribeId));

            // 3. Persist to Database
            eventsFacade.create(newEvent);
            
            // 4. Redirect with success
            response.sendRedirect("EventServlet.do"); // Go back to the map to see the new pin!
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("addEvent.jsp?error=true");
        }
    }
}