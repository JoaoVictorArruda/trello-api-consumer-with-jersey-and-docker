package br.com.trabalho_a3;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

@WebServlet(name = "MainServlet", value = "/main-servlet")
public class MainServlet extends DefaultServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        WebResource wr;
        Client c = Client.create();
        PrintWriter out = response.getWriter();
        if(!(request.getParameter("list") == null)) {
            String list = request.getParameter("list");
            wr = c.resource(baseUrl+"lists/"+list+"/cards?key="+key+"&token="+token);
            String json = wr.get(String.class);
            out.println(json);
        } else {
            String id = request.getParameter("id");
            wr = c.resource(baseUrl+"boards/"+id+"/lists?key="+key+"&token="+token);
            String json = wr.get(String.class);
            out.println(json);
        }
//        response.setContentType("application/json");
//        String id = request.getParameter("id");
//        Client c = Client.create();
//        WebResource wr = c.resource(baseUrl+"lists/"+id+"?key="+key+"&token="+token);
//        String json = wr.get(String.class);
//        PrintWriter out = response.getWriter();
//        out.println(json);
    }

    public void destroy() {
    }

}
