package br.com.trabalho_a3;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

abstract class DefaultServlet extends HttpServlet {
    final String baseUrl = "https://api.trello.com/1/";

    String token = "put your token here";
    String key = "put your key here";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.token = req.getParameter("token");
        this.key = req.getParameter("key");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.token = req.getParameter("token");
        this.key = req.getParameter("key");
    }
}
