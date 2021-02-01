package br.com.trabalho_a3;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ListServlet", value = "/list-servlet")
public class ListServlet extends DefaultServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        super.doPost(request, response);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Client c = Client.create();
        String json;
        String delete = request.getParameter("delete");
        if(delete != null && delete.equals("true")) {
            String id = request.getParameter("id");
            WebResource wr = c.resource(baseUrl+"lists/"+id+"/closed/?key="+key+"&token="+token+"&value=true");
            ClientResponse responseDelete = wr.put(ClientResponse.class);
            json = responseDelete.getEntity(String.class);
        } else {
            String idBoard = request.getParameter("idBoard");
            String name = request.getParameter("nome");
            WebResource wr = c.resource(baseUrl+"lists/?idBoard="+idBoard+"&pos=bottom"+"&key="+key+"&token="+token+"&name="+name);
            ClientResponse responsePost = wr.post(ClientResponse.class);
            json = responsePost.getEntity(String.class);
         }
        out.println(json);
    }

    public void destroy() {
    }

}
