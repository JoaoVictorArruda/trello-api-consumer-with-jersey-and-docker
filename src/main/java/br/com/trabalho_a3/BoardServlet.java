package br.com.trabalho_a3;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@WebServlet(name = "BoardServlet", value = "/board-servlet")
public class BoardServlet extends DefaultServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        super.doGet(request, response);
        response.setContentType("application/json");
        Client c = Client.create();
        WebResource wr = c.resource(baseUrl+"members/me/boards?key="+key+"&token="+token);
        String json = wr.get(String.class);
        PrintWriter out = response.getWriter();
        out.println(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        super.doPost(request, response);
        response.setContentType("application/json");
        Client c = Client.create();
        String nome = request.getParameter("nome");
        PrintWriter out = response.getWriter();
        String json;
        String delete = request.getParameter("delete");
        if(delete != null && delete.equals("true")) {
            WebResource wr = c.resource(baseUrl+"boards/"+nome+"/?key="+key+"&token="+token);
            ClientResponse responseDelete = wr.delete(ClientResponse.class);
            json = responseDelete.getEntity(String.class);

        } else {
            WebResource wr = c.resource(baseUrl+"boards/?name="+nome+"&prefs_permissionLevel=public"+"&key="+key+"&token="+token);
            ClientResponse responsePost = wr.post(ClientResponse.class);
            json = responsePost.getEntity(String.class);
        }
        out.println(json);
    }

    public void destroy() {
    }

}
