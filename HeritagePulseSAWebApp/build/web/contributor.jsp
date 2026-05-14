<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Heritage Pulse | Contribute</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
</head>
<body>

    <nav class="navbar">
        <div class="logo">HeritagePulse SA</div>
        <ul class="nav-links">
            <li><a href="index.html">Home</a></li>
            <li><a href="ArchiveServlet.do">Archive</a></li>
            <li><a href="SpotlightServlet.do">Spotlight</a></li>
            <li><a href="EventServlet.do">Culture GPS</a></li>
            
        </ul>
    </nav>

    <header class="search-container">
        <h1>Share Your Heritage</h1>
        <p>Digitize a remedy, story, or traditional attire to preserve it for the next generation.</p>
    </header>

    <main class="results-section" style="display: flex; justify-content: center;">
        <div class="remedy-card" style="width: 100%; max-width: 600px; padding: 40px;">
            
            <c:if test="${not empty uploadMessage}">
                <div style="background: #d4edda; color: #155724; padding: 15px; border-radius: 5px; margin-bottom: 20px;">
                    ${uploadMessage}
                </div>
            </c:if>

            <form action="UploadServlet.do" method="POST" enctype="multipart/form-data">
                
                <h3 style="color: var(--primary-brown); margin-bottom: 15px; border-bottom: 2px solid #eee; padding-bottom: 5px;">1. The Basics</h3>
                
                <div class="form-group" style="margin-bottom: 15px;">
                    <label><strong>Title of Item / Story:</strong></label>
                    <input type="text" name="title" required style="width: 100%; padding: 10px; margin-top: 5px; border-radius: 5px; border: 1px solid #ccc;">
                </div>

                <div style="display: flex; gap: 15px; margin-bottom: 15px;">
                    <div class="form-group" style="flex: 1;">
                        <label><strong>Category:</strong></label>
                        <select name="category" required style="width: 100%; padding: 10px; margin-top: 5px; border-radius: 5px;">
                            <option value="Apothecary">Apothecary (Medicine)</option>
                            <option value="Chronicle">Chronicle (Folklore/Story)</option>
                            <option value="Attire">Traditional Attire</option>
                            <option value="Cuisine">Cuisine & Recipes</option>
                        </select>
                    </div>

                    <div class="form-group" style="flex: 1;">
                        <label><strong>Lineage (Tribe):</strong></label>
                        <select name="tribeId" required style="width: 100%; padding: 10px; margin-top: 5px; border-radius: 5px;">
                        <option value="1">Venda</option>
                         <option value="2">Xhosa</option>
                          <option value="3">Zulu</option>
                              <option value="4">Pedi (Northern Sotho)</option>
                              <option value="5">Tswana</option>
                             <option value="6">Ndebele</option>
                              <option value="7">Sotho (Southern Sotho)</option>
                              <option value="8">Tsonga</option>
                             <option value="9">Swati</option>
                                 <option value="10">Khoisan</option>
                             <option value="11">Other / Mixed Heritage</option>
                            </select>
                    </div>
                </div>

                <h3 style="color: var(--primary-brown); margin-top: 30px; margin-bottom: 15px; border-bottom: 2px solid #eee; padding-bottom: 5px;">2. The Details</h3>
                
                <div class="form-group" style="margin-bottom: 15px;">
                    <label><strong>Full Description / Ingredients:</strong></label>
                    <p style="font-size: 0.85rem; color: #666; margin: 5px 0;">Share the historical context, how it is made, or the story behind it.</p>
                    <textarea name="description" rows="6" required style="width: 100%; padding: 10px; border-radius: 5px; border: 1px solid #ccc;"></textarea>
                </div>

                <h3 style="color: var(--primary-brown); margin-top: 30px; margin-bottom: 15px; border-bottom: 2px solid #eee; padding-bottom: 5px;">3. Visual Evidence</h3>

                <div class="form-group" style="margin-bottom: 25px;">
                    <label><strong>Upload Photo or Video:</strong></label>
                    <input type="file" name="mediaFile" accept="image/*,video/mp4" required style="width: 100%; padding: 10px; margin-top: 5px; background: #f9f9f9; border: 1px dashed #ccc; border-radius: 5px;">
                </div>

                <button type="submit" class="btn-search btn-full" style="font-size: 1.1rem; padding: 15px;">Submit to Archive Elders</button>
            </form>
        </div>
    </main>

</body>
</html>