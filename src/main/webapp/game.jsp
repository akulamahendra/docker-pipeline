<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.cardie.model.GameState" %>
<%@ page import="com.cardie.model.Card" %>
<%
    GameState gameState = (GameState) session.getAttribute("gameState");
    String playerName = (String) session.getAttribute("playerName");
    
    if (gameState == null || playerName == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cardie - Play</title>
    <link rel="stylesheet" href="css/style.css?v=7">
</head>
<body class="dark-mode">
    <div class="top-bar">
        <div class="player-info">
            <span>Player: <%= playerName %></span>
            <span>Time: <span id="timer">00:00</span></span>
            <span>Matches: <span id="matches">0</span>/<%= gameState.getTotalPairs() %></span>
        </div>
        <button id="themeToggle" class="theme-toggle">☀️ Light Mode</button>
    </div>
    
    <div class="container">
        <div class="game-board" id="gameBoard">
            <% 
                int index = 0;
                for (Card card : gameState.getCards()) {
            %>
                <div class="card" data-index="<%= index %>" data-id="<%= card.getId() %>" onclick="flipCard(this)">
                    <div class="card-inner">
                        <div class="card-back">
                            <span>🎴</span>
                        </div>
                        <div class="card-front">
                            <%= card.getEmoji() %>
                        </div>
                    </div>
                </div>
            <% 
                    index++;
                }
            %>
        </div>
    </div>
    
    <div id="winModal" class="modal">
        <div class="modal-content">
            <h2>🎉 Congratulations! 🎉</h2>
            <p>You won!</p>
            <p>Time: <span id="finalTime"></span></p>
            <p>Your Rank: #<span id="finalRank"></span></p>
            <div class="modal-buttons">
                <a href="index.jsp" class="btn btn-primary">Play Again</a>
                <a href="leaderboard" class="btn btn-secondary">Leaderboard</a>
            </div>
        </div>
    </div>
    
    <script>
        const totalPairs = <%= gameState.getTotalPairs() %>;
    </script>
    <script src="js/game.js"></script>
    <script src="js/theme.js"></script>
</body>
</html>
