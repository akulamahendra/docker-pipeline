<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.cardie.model.Player" %>
<%@ page import="java.util.List" %>
<%
    @SuppressWarnings("unchecked")
    List<Player> players = (List<Player>) request.getAttribute("players");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cardie - Leaderboard</title>
    <link rel="stylesheet" href="css/style.css?v=7">
</head>
<body class="dark-mode">
    <div class="container">
        <div class="leaderboard-screen">
            <h1 class="title">🏆 LEADERBOARD</h1>
            
            <% if (players == null || players.isEmpty()) { %>
                <div class="empty-message">
                    <p>No scores yet. Be the first!</p>
                </div>
            <% } else { %>
                <table class="leaderboard-table">
                    <thead>
                        <tr>
                            <th>Rank</th>
                            <th>Player</th>
                            <th>Time</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            for (int i = 0; i < players.size(); i++) {
                                Player player = players.get(i);
                                String rankEmoji;
                                if (i == 0) rankEmoji = "🥇";
                                else if (i == 1) rankEmoji = "🥈";
                                else if (i == 2) rankEmoji = "🥉";
                                else rankEmoji = String.valueOf(i + 1);
                        %>
                            <tr>
                                <td><%= rankEmoji %></td>
                                <td><%= player.getName() %></td>
                                <td><%= player.getFormattedTime() %></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } %>
            
            <div class="button-group">
                <a href="index.jsp" class="btn btn-primary">Play Game</a>
                <button id="themeToggle" class="theme-toggle">☀️ Light Mode</button>
            </div>
        </div>
    </div>
    
    <script src="js/theme.js"></script>
</body>
</html>
