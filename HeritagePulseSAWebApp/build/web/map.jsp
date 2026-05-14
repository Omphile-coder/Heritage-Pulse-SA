<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Heritage Pulse SA | Cultural GPS</title>
    
    <link rel="stylesheet" type="text/css" href="style.css">
    
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
    <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
    
    <script src="js/heritage-pulse.js" defer></script>
</head>
<body>

    <nav class="navbar">
        <div class="logo">HeritagePulse SA</div>
        <ul class="nav-links">
            <li><a href="index.html">Home</a></li>
            <li><a href="ArchiveServlet.do">Archive</a></li>
            <li><a href="apothecary.jsp">Apothecary</a></li>
            <li><a href="EventServlet.do">Culture GPS</a></li>
        </ul>
    </nav>

    <header class="search-container">
        <h1>Heritage Pulse GPS</h1>
        <div id="heritage-map" style="height: 450px; width: 90%; margin: 20px auto; border-radius: 15px; border: 3px solid var(--primary-brown);"></div>
    </header>

    <main class="results-section">
        <div id="map-display" style="text-align: center; margin-bottom: 20px;">
            <p id="gps-status">📍 Detecting your location pulse...</p>
            <c:if test="${not empty param.lat}">
                <small style="color: var(--accent-brown);">Active Coordinates: ${param.lat}, ${param.lng}</small>
            </c:if>
        </div>

        <div class="header-flex" style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
            <h2>Nearby Events</h2>
            <button onclick="initiateCulturalGPS()" class="btn-search" style="border-radius: 5px;">Refresh Pulse</button>
        </div>

        <div class="results-grid">
            <c:choose>
                <c:when test="${not empty nearbyEvents}">
                    <c:forEach var="event" items="${nearbyEvents}">
                        <div class="remedy-card">
                         <span class="category-tag">Cultural Event</span>
                        <h3 style="margin: 10px 0;">${event.eventTitle}</h3>
                            <p><strong>Date & Time:</strong> ${event.eventDate}</p>
                            <p style="margin-top: 10px; font-size: 0.85rem; color: #666;">
                                This event is pinned based on your current location and heritage interests.
                            </p>
                           
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div style="grid-column: 1/-1; text-align: center; padding: 40px;">
                        <p>No cultural events detected in your immediate radius.</p>
                        <p style="font-size: 0.9rem; color: #888;">Try moving to a different region or following more traditions.</p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </main>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Initialize map centered on South Africa or user coordinates
            var initialLat = ${not empty param.lat ? param.lat : -28.4793};
            var initialLng = ${not empty param.lng ? param.lng : 24.6727};
            var zoomLevel = ${not empty param.lat ? 10 : 5};

            var map = L.map('heritage-map').setView([initialLat, initialLng], zoomLevel);

            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '© OpenStreetMap'
            }).addTo(map);

            // Add pins from the Java database list
            <c:forEach var="event" items="${nearbyEvents}">
                L.marker([${event.latitude}, ${event.longitude}])
                    .addTo(map)
                    .bindPopup("<b>${event.eventTitle}</b><br>${event.eventDate}");
            </c:forEach>

            // If user location is active, show a circle for "You are here"
            <c:if test="${not empty param.lat}">
                L.circle([${param.lat}, ${param.lng}], {
                    color: 'red',
                    fillColor: '#f03',
                    fillOpacity: 0.5,
                    radius: 2000
                }).addTo(map).bindPopup("Your Location");
            </c:if>
        });
    </script>

    <footer>
        <p>&copy; 2026 Heritage Pulse SA | Preserving South African Wisdom </p>
    </footer>

</body>
</html>