package com.cardie.servlet;

import com.cardie.model.Card;
import com.cardie.model.GameState;
import com.cardie.model.Player;
import com.cardie.util.LeaderboardManager;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class FlipCardServlet extends HttpServlet {
    
    private final Gson gson = new Gson();
    private final LeaderboardManager leaderboardManager = new LeaderboardManager();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        GameState gameState = (GameState) session.getAttribute("gameState");
        
        Map<String, Object> result = new HashMap<>();
        
        if (gameState == null) {
            result.put("error", "No game session found");
            out.print(gson.toJson(result));
            return;
        }
        
        String cardIndexStr = request.getParameter("cardIndex");
        if (cardIndexStr == null) {
            result.put("error", "Card index required");
            out.print(gson.toJson(result));
            return;
        }
        
        int cardIndex = Integer.parseInt(cardIndexStr);
        Card card = gameState.getCards().get(cardIndex);
        
        if (card.isMatched() || card.isFlipped()) {
            result.put("success", false);
            out.print(gson.toJson(result));
            return;
        }
        
        card.setFlipped(true);
        
        if (gameState.getFirstCard() == null) {
            gameState.setFirstCard(card);
            result.put("success", true);
            result.put("wait", false);
        } else if (gameState.getSecondCard() == null) {
            gameState.setSecondCard(card);
            result.put("success", true);
            result.put("wait", true);
            
            Card first = gameState.getFirstCard();
            Card second = gameState.getSecondCard();
            
            boolean isMatch = first.getId() == second.getId();
            result.put("match", isMatch);
            
            if (isMatch) {
                first.setMatched(true);
                second.setMatched(true);
                gameState.incrementMatches();
                
                if (gameState.getMatchesFound() == gameState.getTotalPairs()) {
                    gameState.setGameFinished(true);
                    result.put("gameFinished", true);
                    
                    // Save score
                    String playerName = (String) session.getAttribute("playerName");
                    if (playerName != null) {
                        Player player = new Player(playerName, gameState.getElapsedTime());
                        leaderboardManager.addPlayer(player);
                        int rank = leaderboardManager.getPlayerRank(player);
                        result.put("rank", rank);
                        result.put("time", player.getFormattedTime());
                    }
                }
            } else {
                // Not a match - reset flipped state
                first.setFlipped(false);
                second.setFlipped(false);
            }
            
            gameState.clearSelectedCards();
        }
        
        result.put("matchesFound", gameState.getMatchesFound());
        result.put("elapsedTime", gameState.getElapsedTime());
        
        out.print(gson.toJson(result));
    }
}
