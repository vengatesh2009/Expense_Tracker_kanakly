package koli;

import java.io.IOException;
import java.sql.Connection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Backend.database.Repo;

@WebServlet("/Koli")
public class Koli extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try {
            Connection conn = Repo.getInstance();
            if (conn != null && !conn.isClosed()) {
                response.getWriter().write("<h2>Success! Connected to MySQL database successfully.</h2>");
            } else {
                response.getWriter().write("<h2>Connection failed: Connection object is null or closed.</h2>");
            }
        } catch (Exception e) {
            response.getWriter().write("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}