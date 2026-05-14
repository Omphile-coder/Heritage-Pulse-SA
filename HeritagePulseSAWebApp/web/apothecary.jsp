<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Heritage Pulse SA | Apothecary</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

    <nav class="navbar">
        <div class="logo">HeritagePulse SA</div>
        <ul class="nav-links">
            <li><a href="index.html">Home</a></li>
            <li><a href="EventServlet.do">Culture GPS</a></li>
            <li><a href="apothecary.jsp">Apothecary</a></li>
            
            <li><a href="ArchiveServlet.do">Archive</a></li>
            <li><a href="SpotlightServlet.do">Spotlight</a></li> 
         
         
         <li><a href="UploadServlet.do" style="background-color: var(--primary-brown); color: white; padding: 8px 15px; border-radius: 5px; font-weight: bold;">Share a Story🤍</a></li>
        </ul>
    </nav>

    <header class="search-container">
        <h1>Verified Remedy Directory</h1>
        <p>Digitizing South African indigenous medicine for future generations.</p>
        <div class="search-box">
            
            
            <form action="ApothecaryServlet.do" method="POST">
                <input type="text" name="remedySearch" placeholder="Enter indigenous name (e.g., Lengana)..." required>
                <button type="submit" class="btn-search">Search Archive</button>
            </form>
            
            
        </div>
    </header>

    <main class="results-section">
        <div class="results-grid">
            <c:choose>
                <c:when test="${not empty remedyResults}">
                    <c:forEach var="remedy" items="${remedyResults}">
                        <div class="remedy-card">
                            <h3>${remedy.indigenousName}</h3>
                            <span class="scientific-name">Scientific: ${remedy.scientificName}</span>
                            <p><strong>Traditional Usage:</strong><br>${remedy.traditionalUsage}</p>
                            
                            <div class="safety-disclaimer">
                                ⚠ SAFETY CHECK: ${remedy.safetyDisclaimer}
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:if test="${pageContext.request.method == 'POST'}">
                        <p style="text-align: center; color: #777;">No remedies found in the archive for that search. Try a different term.</p>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </div>
    </main>

    <footer >
        <p>&copy; 2026 Heritage Pulse SA | Preserving Our Roots</p>
    </footer>

</body>
</html>