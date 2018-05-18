package proiectPao.loginServlets;

import java.io.IOException;
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
 * Servlet implementation class Register
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/myDb")
	private DataSource dbRes;     
 
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String firstName=request.getParameter("first");
		String lastName=request.getParameter("last");
		
		try(Connection con=dbRes.getConnection();
			PreparedStatement ps=con.prepareStatement(
			"select * from user_hotel where username='" + username + "' and password='" + password + "'");
			ResultSet rs=ps.executeQuery()){
			if(!rs.next()) {
				int nr=getAllUsers();
				if(nr==-1) {
					//registration failed
				}
				nr++;
				System.out.println("salut");
				try(PreparedStatement ps2=con.prepareStatement(
					"insert into user_hotel values (" + username + "," + password + 
					"," + email + "," + lastName + "," + firstName + "," + "client," + nr +")")){
					int result=ps2.executeUpdate();
					if(result!=1)
					{
						//registration failed
					}
					this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
				}
			}
			else {
				//registration failed - username already used
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public int getAllUsers() {
		try(Connection con=dbRes.getConnection();
			PreparedStatement ps=con.prepareStatement("select count(*) no_users from user_hotel");
			ResultSet rs=ps.executeQuery()){
			rs.next();
			return rs.getInt("no_users");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
