package demo.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component(service = Servlet.class, property = {
        "sling.servlet.resourceTypes=sling/servlet/default",
        "sling.servlet.selectors=hello"
})
public class HelloWorld extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html");

        Session session = request.getResourceResolver().adaptTo(Session.class);


        try(PrintWriter writer = response.getWriter()) {
            writer.write("<p>Hello World</p>"+ session.getUserID());
        }
    }
}
