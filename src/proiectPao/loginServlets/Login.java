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
		String username,password,privileges="";
		username=request.getParameter("username");
		password=request.getParameter("password");
		try ( Connection con=dbRes.getConnection();
			 
				PreparedStatement ps=con.prepareStatement(
				"select * from user_hotel where username='" + username + "' and password='" + password + "'");
				  ResultSet rs=ps.executeQuery())
			{
				if(rs.next()) {
					privileges=rs.getString("role");
					System.out.println(privileges);
					request.getSession().setAttribute("username", username);
					request.getSession().setAttribute("id_user", rs.getInt("id_user"));
					ServletContext context=this.getServletContext();
					System.out.println(request.getParameter("source"));
					
					switch(privileges) {
						case "admin":
							context.getRequestDispatcher("/adminmode.jsp").forward(request, response);
							break;
						case "receptionist":
							context.getRequestDispatcher("/MainPageRecep.jsp").forward(request, response);
							break;
						case "client":
							if(request.getParameter("source")!=null)
								context.getRequestDispatcher("/PresentationPage.jsp");
							context.getRequestDispatcher("/MainPage.jsp").forward(request, response);
							break;
						case "webservice":
							context.getRequestDispatcher("/webservice.jsp").forward(request, response);
							break;
						default:
							System.out.println("ABRACADABRA");
							break;
					}	
				}
				else {
					request.setAttribute("failed", "failed");
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
