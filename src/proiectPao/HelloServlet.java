package proiectPao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class HelloServlet
 */
//You can configure one or multiple 'URL Patterns' can access this Servlet.
     @WebServlet(urlPatterns = {"/helloExample", "/annExample" }, initParams = {
     @WebInitParam(name = "emailSupport1", value = "abc@example.com"),
     @WebInitParam(name = "emailSupport2", value = "tom@example.com") })
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/myDb")
	private DataSource dbRes;  
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
 
    	double nr=5;
		try ( Connection con=dbRes.getConnection();
			  PreparedStatement ps=con.prepareStatement("Select * from employees");
			  ResultSet rs=ps.executeQuery())
		{
			rs.next();
			nr=rs.getDouble("employee_id");
			System.out.println("200");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("numar", nr);
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

}
