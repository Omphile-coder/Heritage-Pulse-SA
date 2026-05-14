<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Heritage Pulse SA | Living Archive</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

    <nav class="navbar">
        <div class="logo">HeritagePulse SA</div>
        <ul class="nav-links">
            <li><a href="index.html">Home</a></li>
            <li><a href="ArchiveServlet.do">Archive</a></li>
            <li><a href="apothecary.jsp">Apothecary</a></li>
            <li><a href="EventServlet.do">Culture GPS</a></li>
           
            <li><a href="ArchiveServlet.do">Archive</a></li>
             <li><a href="SpotlightServlet.do">Spotlight</a></li> 
         
         
         <li><a href="UploadServlet.do" style="background-color: var(--primary-brown); color: white; padding: 8px 15px; border-radius: 5px; font-weight: bold;">Share a Story🤍</a></li>
        </ul>
    </nav>

    <header class="search-container">
        <h1>The Living Archive</h1>
        
        <div style="text-align: center; margin: 20px 0;">
    <p>Have a traditional remedy, attire, or story passed down in your family?</p>
    <a href="UploadServlet.do" class="btn-search" style="display: inline-block; padding: 10px 20px; font-size: 1.1rem; text-decoration: none;">
        Contribute to the Archive
    </a>
</div>
        <p>Explore the attire and cuisine of South Africa's diverse ethnic landscapes.</p>
    </header>

    <div class="results-section">
        <div class="filter-bar" >
            
            
            <form action="ArchiveServlet.do" method="GET" s>
                <select name="tribeId" >
                    
                    
                    <option value="">Select Tribe</option>
                    <c:forEach var="t" items="${tribes}">
                        <option value="${t.tribeId}">${t.tribeName}</option>
                    </c:forEach>
                        
                        
                </select>
                
                <select name="category" >
                    <option value="">Category</option>
                    <option value="Attire">Traditional Attire</option>
                    <option value="Cuisine">Indigenous Cuisine</option>
                </select>
                
                <button type="submit" class="btn-search" >Filter</button>
            </form>
        </div>

        <div class="results-grid">
            <c:forEach var="item" items="${archiveItems}">
                
                <div class="remedy-card">
                    <h3>${item.title}</h3>
                    <span class="scientific-name">${item.category}</span>
                    <p>${item.description}</p>
                </div>
                
            </c:forEach>
        </div>
    </div>

    <footer>
        <p>&copy; 2026 Heritage Pulse SA | Preserving Our Roots</p>
    </footer>

</body>
</html>