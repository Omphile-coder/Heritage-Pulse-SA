<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Heritage Pulse | Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
</head>
<body>
    <nav class="navbar">
        <div class="logo">Admin Portal: Heritage Pulse SA</div>
        <ul class="nav-links">
            <li><a href="LogoutServlet.do">Logout</a></li>
        </ul>
    </nav>

    <main class="results-section">
        
        <div style="display: flex; gap: 20px; margin-bottom: 30px;">
            <div class="remedy-card" style="flex: 1; text-align: center; border-top: 5px solid #2e7d32;">
                <h3>Cultural Events</h3>
                <p>Register new festivals, weddings, or markets for the Culture GPS.</p>
                <a href="addEvent.jsp" class="btn-search" style="display: inline-block; text-decoration: none; margin-top: 10px;">
                    + Register New Event
                </a>
            </div>
    
            <div class="remedy-card" style="flex: 1; text-align: center; border-top: 5px solid var(--primary-brown);">
                <h3>Archive Items</h3>
                <p>You have <strong>${pendingItems.size()}</strong> submissions awaiting verification.</p>
                <a href="#moderation-queue" class="btn-search" style="display: inline-block; text-decoration: none; margin-top: 10px; background: #5d4037;">
                  
                </a>
            </div>
        </div>
        
        <h2 id="moderation-queue">Moderation Queue</h2>
        <p style="margin-bottom: 20px;">Verify cultural submissions before they are added to the Living Archive.</p>

        <c:if test="${not empty sessionScope.adminMessage}">
            <div style="background: #e3f2fd; color: #0d47a1; padding: 15px; border-radius: 5px; margin-bottom: 20px; border-left: 5px solid #1976d2;">
                ${sessionScope.adminMessage}
            </div>
            <c:remove var="adminMessage" scope="session" />
        </c:if>

        <table class="admin-table" style="width: 100%; border-collapse: collapse; background: white; box-shadow: 0 4px 15px rgba(0,0,0,0.05);">
            <tr style="background: var(--primary-brown); color: white; text-align: left;">
                <th style="padding: 15px;">Title</th>
                <th style="padding: 15px;">Category</th>
                <th style="padding: 15px;">File Path</th>
                <th style="padding: 15px;">Actions</th>
            </tr>
            <c:forEach var="item" items="${pendingItems}">
                <tr style="border-bottom: 1px solid #ddd;">
                    <td style="padding: 15px;"><strong>${item.title}</strong></td>
                    <td style="padding: 15px;">${item.category}</td>
                    <td style="padding: 15px;"><small>${item.filePath}</small></td>
                    <td style="padding: 15px;">
                        <form action="AdminServlet.do" method="POST" style="display:inline;">
                            <input type="hidden" name="itemId" value="${item.itemId}">
                            
                            <button type="submit" name="action" value="Approve" class="btn-search" style="background: #2e7d32; padding: 8px 12px; border-radius: 4px; margin-right: 5px;">
                                Approve
                            </button>
                            
                            <button type="submit" name="action" value="Reject" class="btn-search" style="background: #c62828; padding: 8px 12px; border-radius: 4px;" 
                                    onclick="return confirm('WARNING: Are you sure you want to reject and permanently delete this submission?');">
                                Reject
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </main>
</body>
</html>