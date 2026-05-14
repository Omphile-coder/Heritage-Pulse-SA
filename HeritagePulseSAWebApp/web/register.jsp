<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register - Heritage Pulse SA</title>
    
    <style>
        /* --- Heritage Pulse Registration Styles --- */
        :root {
            --primary-color: #D35400; /* Change this hex code to match your site's main color */
            --primary-hover: #A04000;
            --bg-color: #F8F9F9;
            --text-color: #2C3E50;
            --border-color: #BDC3C7;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: var(--bg-color);
            color: var(--text-color);
            margin: 0;
            padding: 0;
        }

        .auth-wrapper {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 80vh;
            padding: 20px;
        }

        .registration-container {
            background: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 450px;
            border-top: 5px solid var(--primary-color);
        }

        .registration-container h2 {
            text-align: center;
            margin-bottom: 30px;
            color: var(--text-color);
            font-size: 28px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            font-size: 14px;
        }

        .form-group input, 
        .form-group select {
            width: 100%;
            padding: 12px;
            border: 1px solid var(--border-color);
            border-radius: 6px;
            font-size: 16px;
            box-sizing: border-box; /* Ensures padding doesn't break width */
            transition: border-color 0.3s;
        }

        .form-group input:focus, 
        .form-group select:focus {
            outline: none;
            border-color: var(--primary-color);
            box-shadow: 0 0 5px rgba(211, 84, 0, 0.3);
        }

        .btn-submit {
            width: 100%;
            padding: 14px;
            background-color: var(--primary-color);
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s;
            margin-top: 10px;
        }

        .btn-submit:hover {
            background-color: var(--primary-hover);
        }

        .login-link {
            text-align: center;
            margin-top: 20px;
            font-size: 14px;
        }

        .login-link a {
            color: var(--primary-color);
            text-decoration: none;
            font-weight: bold;
        }

        .login-link a:hover {
            text-decoration: underline;
        }

        /* Error Message Styling */
        .error-message {
            background-color: #FDF2F0;
            color: #E74C3C;
            padding: 10px;
            border-left: 4px solid #E74C3C;
            margin-bottom: 20px;
            border-radius: 4px;
            font-size: 14px;
            text-align: center;
        }
    </style>
</head>
<body>

    <div class="auth-wrapper">
        <div class="registration-container">
            <h2>Join Heritage Pulse</h2>
            
            <% if ("true".equals(request.getParameter("error"))) { %>
                <div class="error-message">
                    An error occurred while creating your account. Please try a different username or email.
                </div>
            <% } %>

            <form action="RegisterServlet.do" method="POST">
                
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" placeholder="e.g. Karabo123" required>
                </div>
                
                <div class="form-group">
                    <label for="email">Email Address</label>
                    <input type="email" id="email" name="email" placeholder="you@example.com" required>
                </div>
                
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" placeholder="Create a strong password" required>
                </div>
                
                <div class="form-group">
                    <label for="tribeId">Which heritage are you most interested in?</label>
                    <select id="tribeId" name="tribeId">
                        <option value="1">Zulu</option>
                        <option value="2">Xhosa</option>
                        <option value="3">Venda</option>
                        <option value="4">Pedi</option>
                        <option value="5">Tswana</option>
                        <option value="6">Ndebele</option>
                        <option value="7">Swati</option>
                        <option value="8">Tsonga</option>
                        <option value="9">Sotho</option>
                        <option value="10">Khoisan</option>
                        <option value="11">Other / All</option>
                    </select>
                </div>
                
                <button type="submit" class="btn-submit">Create Account</button>
                
            </form>
            
            <div class="login-link">
                Already have an account? <a href="login.jsp">Sign in here</a>
            </div>
            
        </div>
    </div>

    </body>
</html>