package br.com.trabalho_a3;

import javax.servlet.http.HttpServlet;

abstract class DefaultServlet extends HttpServlet {
    final String baseUrl = "https://api.trello.com/1/";

    final String token = "put your token here";
    final String key = "put your key here";

}
