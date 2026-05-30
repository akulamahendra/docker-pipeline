<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cardie - Memory Game</title>
    <link rel="stylesheet" href="css/style.css?v=7">
</head>
<body class="dark-mode">
    <div class="container">
        <div class="welcome-screen">
            <h1 class="title">🎮 CARDIE 🎮</h1>
            <p class="subtitle">Memory Card Game</p>
            <p class="instruction">Enter your name to start:</p>
            
            <% if (request.getParameter("error") != null) { %>
                <p class="error-message">Please enter your name!</p>
            <% } %>
            
            <form action="game" method="post">
                <input type="text" name="playerName" placeholder="Your name" required autofocus>
                <button type="submit" class="btn btn-primary">Start Game</button>
            </form>
            
            <a href="leaderboard" class="btn btn-secondary">View Leaderboard</a>
            
            <button id="themeToggle" class="theme-toggle">☀️ Light Mode</button>
        </div>
    </div>
    
    <script src="js/theme.js"></script>
</body>
</html>
