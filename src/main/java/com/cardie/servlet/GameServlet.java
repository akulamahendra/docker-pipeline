package com.cardie.servlet;

import com.cardie.model.GameState;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GameServlet extends HttpServlet {
    
    private static final List<String> ANIMAL_EMOJIS = Arrays.asList(
        "🐶", // Dog
        "🐱", // Cat
        "🐭", // Mouse
        "🐹", // Hamster
        "🐰", // Rabbit
        "🦊", // Fox
        "🐻", // Bear
        "🐼"  // Panda
    );

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String playerName = request.getParameter("playerName");
        
        if (playerName == null || playerName.trim().isEmpty()) {
            response.sendRedirect("index.jsp?error=name");
            return;
        }
        
        HttpSession session = request.getSession();
        
        // Create new game state
        GameState gameState = new GameState();
        gameState.initializeCards(ANIMAL_EMOJIS);
        gameState.setStartTime(System.currentTimeMillis());
        gameState.setGameStarted(true);
        
        // Store in session
        session.setAttribute("gameState", gameState);
        session.setAttribute("playerName", playerName);
        
        // Forward to game page
        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }
}
