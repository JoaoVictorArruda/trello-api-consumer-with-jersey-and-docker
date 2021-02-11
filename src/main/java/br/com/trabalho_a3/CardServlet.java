package br.com.trabalho_a3;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CardServlet", value = "/card-servlet")
public class CardServlet extends DefaultServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        super.doPost(request, response);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Client c = Client.create();
        String json;
        String delete = request.getParameter("delete");
        String move = request.getParameter("move");
        if(move != null && move.equals("true")) {
            String idList = request.getParameter("idList");
            String idCard = request.getParameter("idCard");
            WebResource wr = c.resource(baseUrl+"cards/"+idCard+"/?idList="+idList+"&pos=bottom"+"&key="+key+"&token="+token);
            ClientResponse responsePost = wr.put(ClientResponse.class);
            json = responsePost.getEntity(String.class);
        } else if(delete != null && delete.equals("true")) {
            String id = request.getParameter("id");
            WebResource wr = c.resource(baseUrl+"cards/"+id+"/?key="+key+"&token="+token);
            ClientResponse responseDelete = wr.delete(ClientResponse.class);
            json = responseDelete.getEntity(String.class);
        } else {
            String idList = request.getParameter("list");
            String name = request.getParameter("nome");
            name = name.replace(' ', '_');
            WebResource wr = c.resource(baseUrl+"cards/?idList="+idList+"&pos=bottom"+"&key="+key+"&token="+token+"&name="+name);
            ClientResponse responsePost = wr.post(ClientResponse.class);
            json = responsePost.getEntity(String.class);
        }
        out.println(json);
    }

    public void destroy() {
    }

}
