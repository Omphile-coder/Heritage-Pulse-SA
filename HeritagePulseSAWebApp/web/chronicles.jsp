<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Heritage Pulse | Chronicles</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

    <nav class="navbar">
        <div class="logo">HeritagePulse SA</div>
        <ul class="nav-links">
            <li><a href="index.jsp">Home</a></li>
            <li><a href="ChronicleServlet.do">Chronicles</a></li>
            <li><a href="SpotlightServlet.do">Spotlight</a></li>
       
             <li><a href="ArchiveServlet.do">Archive</a></li>
              <li><a href="SpotlightServlet.do">Spotlight</a></li> <li><a href="apothecary.jsp">Apothecary</a></li>
         
         
         <li><a href="UploadServlet.do" style="background-color: var(--primary-brown); color: white; padding: 8px 15px; border-radius: 5px; font-weight: bold;">Share a Story🤍</a></li>
        </ul>
    </nav>

    <header class="search-container">
        <h1>Heritage Chronicles</h1>
        <p>Deep dives into the history, folklore, and wisdom of our ancestors.</p>
        
        <form action="ChronicleServlet" method="GET" style="margin-top: 20px; max-width: 600px; margin-left: auto; margin-right: auto;">
            <div style="display: flex; gap: 10px; margin-bottom: 10px;">
                <input type="text" name="keyword" placeholder="Search stories, recipes, or rituals..." style="flex: 2; padding: 12px; border-radius: 5px; border: none;">
                
                <select name="tribeId" style="flex: 1; padding: 12px; border-radius: 5px; border: none;">
                    <option value="">All Traditions</option>
                    <c:forEach var="t" items="${tribes}">
                        <option value="${t.tribeId}" ${param.tribeId == t.tribeId ? 'selected' : ''}>${t.tribeName}</option>
                    </c:forEach>
                </select>
                
                <button type="submit" class="btn-search" style="border-radius: 5px;">Search</button>
            </div>
            <a href="ChronicleServlet" style="color: white; font-size: 0.85rem; text-decoration: underline;">Clear Filters</a>
        </form>
    </header>

    <main class="results-section">
        <div class="chronicle-container">
            <c:choose>
                <c:when test="${not empty writtenStories}">
                    <c:forEach var="story" items="${writtenStories}">
                        <article class="story-entry">
                            <div class="story-header">
                                <span class="category-tag">${story.category}</span>
                                <h2>${story.title}</h2>
                            </div>
                            
                            <div class="story-content">
                                <p>${story.description}</p>
                            </div>
                            
                            <hr class="story-divider">
                        </article>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div style="text-align: center; padding: 40px;">
                        <h3 style="color: var(--primary-brown);">No chronicles found matching your search.</h3>
                        <p>Try using different keywords or browsing all traditions.</p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </main>

    <footer>
        <p>&copy; 2026 Heritage Pulse SA</p>
    </footer>

</body>
</html>