package proiectPao;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
//You can configure one or multiple 'URL Patterns' can access this Servlet.
     @WebServlet(urlPatterns = {"/helloExample", "/annExample" }, initParams = {
     @WebInitParam(name = "emailSupport1", value = "abc@example.com"),
     @WebInitParam(name = "emailSupport2", value = "tom@example.com") })
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
    private String emailSupport1;
 
    public HelloServlet() {
    }
 
    // This method is always called once after the Servlet object is created.
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
 
        // Get the value of the initialization parameter of the Servlet.
        // (According to the Configuration of this Servlet in web.xml).
        this.emailSupport1 = config.getInitParameter("emailSupport1");
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Get the initialization parameter's value in a different way.
        String emailSupport2 = this.getServletConfig().getInitParameter("emailSupport2");
 
        ServletOutputStream out = response.getOutputStream();
 
        out.println("<html>");
        out.println("<head><title>Init Param</title></head>");
 
        out.println("<body>");
        out.println("<h3>Servlet with Annotation configuration</h3>");
        out.println("<p>emailSupport1 = " + this.emailSupport1 + "</p>");
        out.println("<p>emailSupport2 = " + emailSupport2 + "</p>");
        out.println("</body>");
        out.println("<html>");
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

}
