<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Heritage Pulse | Add Event</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
</head>
<body>
    <nav class="navbar">
        <div class="logo">Admin: Event Management</div>
        <ul class="nav-links">
            <li><a href="AdminServlet.do">Dashboard</a></li>
            <li><a href="LogoutServlet.do">Logout</a></li>
        </ul>
    </nav>

    <main class="results-section" style="display: flex; justify-content: center;">
        <div class="remedy-card" style="width: 100%; max-width: 550px;">
            <h2>Register New Cultural Event</h2>
            <p>Fill in the details to pin this event on the Culture GPS.</p>

            <form action="AddEventServlet.do" method="POST" style="margin-top: 20px;">
                <label><strong>Event Title:</strong></label>
                <input type="text" name="eventTitle" required style="width: 100%; padding: 10px; margin: 8px 0;">

                <div style="display: flex; gap: 10px;">
                    <div style="flex: 1;">
                        <label><strong>Latitude:</strong></label>
                        <input type="text" name="latitude" placeholder="-23.904" required style="width: 100%; padding: 10px;">
                    </div>
                    <div style="flex: 1;">
                        <label><strong>Longitude:</strong></label>
                        <input type="text" name="longitude" placeholder="29.468" required style="width: 100%; padding: 10px;">
                    </div>
                </div>

                <label style="display: block; margin-top: 15px;"><strong>Event Date & Time:</strong></label>
                <input type="datetime-local" name="eventDate" required style="width: 100%; padding: 10px;">

                <label style="display: block; margin-top: 15px;"><strong>Host Tribe:</strong></label>
                <select name="tribeId" required style="width: 100%; padding: 10px;">
                    <option value="1">Venda</option>
                    <option value="2">Xhosa</option>
                    <option value="3">Zulu</option>
                    <option value="4">Pedi</option>
                    <option value="5">Tswana</option>
                </select>

                <button type="submit" class="btn-search btn-full" style="margin-top: 25px;">Publish Event</button>
            </form>
        </div>
    </main>
</body>
</html>