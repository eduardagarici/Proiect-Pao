package proiectPao.loginServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Login
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/myDb")
	private DataSource dbRes;  
    
	public Login() {
    }
    // This method is always called once after the Servlet object is created.
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username,password,privileges;
		username=request.getParameter("username");
		password=request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		try ( Connection con=dbRes.getConnection();
			  PreparedStatement ps=con.prepareStatement("select * from employees");
				//PreparedStatement ps=con.prepareStatement(
				//	"Select * from Student where username=" + username + "and password=" + password );
				  ResultSet rs=ps.executeQuery())
			{
				System.out.println(200);
				if(rs.isBeforeFirst()) {
					Boolean login=false;
					request.setAttribute("failed",login);
					System.out.println("Salut");
					this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				}
				else {
					rs.next();
					privileges=rs.getString("privileges");
					request.getSession().setAttribute("username", username);
					request.getSession().setAttribute("id_user", rs.getInt("id_user"));
					this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		//this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
