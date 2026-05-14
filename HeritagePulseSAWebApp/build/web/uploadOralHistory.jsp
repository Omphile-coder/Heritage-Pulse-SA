<%-- 
    Document   : uploadOralHistory
    Created on : May 11, 2026, 9:42:32 PM
    Author     : lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="remedy-card" style="max-width: 600px; margin: 50px auto;">
            
         <h2>Record & Upload Oral History</h2>
         
            <p style="color: #8d6e63; font-size: 0.9rem; margin-bottom: 20px;">
           Capture the voice of our elders to preserve traditional wisdom.
             </p>

    <form action="OralHistoryServlet.do" method="POST" enctype="multipart/form-data">
        
        <label>Title of the Story/Ritual:</label>
        <input type="text" name="storyTitle" placeholder="e.g., The Origin of the Rain Queen" required style="width: 100%; padding: 10px; margin-bottom: 15px;">

        <label>Tribe/Tradition:</label>
        <select name="tribeId" style="width: 100%; padding: 10px; margin-bottom: 15px;">
            <c:forEach var="t" items="${tribes}">
                <option value="${t.tribeId}">${t.tribeName}</option>
            </c:forEach>
        </select>

        <label>Select Audio/Video File:</label>
        <input type="file" name="oralFile" accept="audio/*,video/*" required style="margin-bottom: 20px;">

        <button type="submit" class="btn-search" style="width: 100%; border-radius: 5px;">Upload to Archive</button>
        
    </form>
</div>
    </body>
</html>
