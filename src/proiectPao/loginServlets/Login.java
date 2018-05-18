package proiectPao.loginServlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
		try ( Connection con=dbRes.getConnection();
			 
				PreparedStatement ps=con.prepareStatement(
				"select * from user_hotel where username='" + username + "' and password='" + password + "'");
				  ResultSet rs=ps.executeQuery())
			{
				if(rs.next()) {
					System.out.println("merge");
					rs.next();
					privileges=rs.getString("privileges");
					request.getSession().setAttribute("username", username);
					request.getSession().setAttribute("id_user", rs.getDouble("id_user"));
					ServletContext context=this.getServletContext();
					switch(privileges) {
						case "admin":
							context.getRequestDispatcher("/index.jsp").forward(request, response);
							break;
						case "receptionist":
							context.getRequestDispatcher("/index.jsp").forward(request, response);
							break;
						case "client":
							context.getRequestDispatcher("/index.jsp").forward(request, response);
							break;
						case "webservice":
							context.getRequestDispatcher("/index.jsp").forward(request, response);
							break;
						default:
							break;
					}	
				}
				else {
					request.setAttribute("failed", "failed");
					System.out.println("Nu merge");
					this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
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
