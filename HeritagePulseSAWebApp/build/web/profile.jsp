<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Heritage Pulse | My Profile</title>
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
            <li><a href="ProfileServlet.do">My Profile</a></li>
            <li><a href="UploadServlet.do" style="background-color: var(--primary-brown); color: white; padding: 8px 15px; border-radius: 5px; font-weight: bold;">Share a Story</a></li>
            <li><a href="LogoutServlet.do">Logout</a></li>
        </ul>
    </nav>

    <header class="search-container">
        <h1>Welcome, ${user.username}</h1>
        <p>Manage your Cultural Guardian experience.</p>
    </header>

    <main class="results-section" style="display: flex; justify-content: center;">
        <div class="remedy-card" style="width: 100%; max-width: 500px;">
            <h2 style="color: var(--primary-brown); margin-bottom: 20px;">Heritage Interests</h2>
            
            <c:if test="${not empty successMessage}">
                <div style="background: #d4edda; color: #155724; padding: 10px; border-radius: 5px; margin-bottom: 15px;">
                    ${successMessage}
                </div>
            </c:if>

            <form action="ProfileServlet.do" method="POST">
                <div class="form-group">
                    <label><strong>Account Email:</strong></label>
                    <p style="padding: 10px; background: #f9f9f9; border: 1px solid #ddd; border-radius: 5px;">${user.email}</p>
                </div>

                <div class="form-group" style="margin-top: 15px;">
                    <label><strong>Primary Tradition / Tribe Interest:</strong></label>
                    <select name="heritageInterest" class="form-control" style="width: 100%; padding: 10px; margin-top: 5px; border-radius: 5px;">
                        <option value="Venda" ${user.heritageInterest == 'Venda' ? 'selected' : ''}>Venda</option>
                        <option value="Xhosa" ${user.heritageInterest == 'Xhosa' ? 'selected' : ''}>Xhosa</option>
                        <option value="Zulu" ${user.heritageInterest == 'Zulu' ? 'selected' : ''}>Zulu</option>
                        <option value="Pedi" ${user.heritageInterest == 'Pedi' ? 'selected' : ''}>Pedi (Northern Sotho)</option>
                        <option value="Tswana" ${user.heritageInterest == 'Tswana' ? 'selected' : ''}>Tswana</option>
                        <option value="Ndebele" ${user.heritageInterest == 'Ndebele' ? 'selected' : ''}>Ndebele</option>
                        <option value="Sotho" ${user.heritageInterest == 'Sotho' ? 'selected' : ''}>Sotho (Southern Sotho)</option>
                        <option value="Tsonga" ${user.heritageInterest == 'Tsonga' ? 'selected' : ''}>Tsonga</option>
                        <option value="Swati" ${user.heritageInterest == 'Swati' ? 'selected' : ''}>Swati</option>
                        <option value="Khoisan" ${user.heritageInterest == 'Khoisan' ? 'selected' : ''}>Khoisan</option>
                        <option value="Other" ${user.heritageInterest == 'Other' ? 'selected' : ''}>Other / Mixed Heritage</option>
                        <option value="All" ${user.heritageInterest == 'All' ? 'selected' : ''}>All South African Heritage</option>
                    </select>
                </div>

                <button type="submit" class="btn-search btn-full" style="margin-top: 20px;">Save Preferences</button>
            </form>
        </div>
    </main>

</body>
</html>