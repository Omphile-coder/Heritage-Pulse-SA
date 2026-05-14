<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Heritage Pulse | Cultural Spotlight</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

    <nav class="navbar">
        <div class="logo">HeritagePulse SA</div>
        <ul class="nav-links">
            <li><a href="index.html">Home</a></li>
            <li><a href="ArchiveServlet.do">Archive</a></li>
            <li><a href="SpotlightServlet.do">Spotlight</a></li>
            <li><a href="EventServlet.do">Culture GPS</a></li>

             <li><a href="ArchiveServlet.do">Archive</a></li>
            <li><a href="SpotlightServlet.do">Spotlight</a></li> <li><a href="apothecary.jsp">Apothecary</a></li>
         
         
         <li><a href="UploadServlet.do" style="background-color: var(--primary-brown); color: white; padding: 8px 15px; border-radius: 5px; font-weight: bold;">Share a Story🤍</a></li>
        </ul>
    </nav>

    <header class="search-container">
        <h1>South African Mosaic</h1>
        <p>A vibrant glimpse into the colors, sounds, and stories of our people.</p>
    </header>

    <main class="results-section">
        <div class="spotlight-grid">
            
            <c:choose>
                <c:when test="${not empty spotlightItems}">
                    <c:forEach var="item" items="${spotlightItems}">
                        <div class="spotlight-card">
                            <c:choose>
                                <%-- Logic to detect if file is a video --%>
                                <c:when test="${item.filePath.endsWith('.mp4') || item.filePath.endsWith('.mov')}">
                                    <video autoplay muted loop class="spotlight-media">
                                        <source src="${item.filePath}" type="video/mp4">
                                    </video>
                                </c:when>
                                <%-- Otherwise treat as a picture --%>
                                <c:otherwise>
                                    <img src="${item.filePath}" alt="${item.title}" class="spotlight-media">
                                </c:otherwise>
                            </c:choose>
                            
                            <div class="spotlight-overlay">
                                <h3>${item.title}</h3>
                                <p>${item.category}</p>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div style="grid-column: 1 / -1; text-align: center; padding: 50px;">
                        <h3 style="color: var(--primary-brown);">The mosaic is waiting to be built.</h3>
                        <p>Upload some photos or videos in the Contribution Portal, and approve them in the Admin Dashboard to see them here!</p>
                    </div>
                </c:otherwise>
            </c:choose>

        </div>
    </main>

    <footer>
        <p>&copy; 2026 Heritage Pulse SA | Preserving Our Roots</p>
    </footer>

</body>
</html>