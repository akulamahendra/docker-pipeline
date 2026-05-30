package com.cardie.servlet;

import com.cardie.util.LeaderboardManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LeaderboardServlet extends HttpServlet {
    
    private final LeaderboardManager leaderboardManager = new LeaderboardManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setAttribute("players", leaderboardManager.loadLeaderboard());
        request.getRequestDispatcher("/leaderboard.jsp").forward(request, response);
    }
}
