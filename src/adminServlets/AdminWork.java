package adminServlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class AdminWork
 */
@WebServlet(name = "AdminWork", urlPatterns = { "/AdminWork" })
public class AdminWork extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/myDb")
	private DataSource dbRes;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminWork() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String floor = request.getParameter("atfloor");
		String type = request.getParameter("types");
		String price = request.getParameter("price");
		String v1 = request.getParameter("pref1");
		String v2 = request.getParameter("pref2");
		String v3 = request.getParameter("pref3");
		String v4 = request.getParameter("pref4");
		String v5 = request.getParameter("pref5");
		String v6 = request.getParameter("pref6");
		String v7 = request.getParameter("pref7");
		String v8 = request.getParameter("pref8");
		int roomnumber = 0;
		try (Connection con = dbRes.getConnection();
				PreparedStatement ps = con.prepareStatement("Select max(id_room) maxim from room_hotel");
				ResultSet rs = ps.executeQuery();) {
			while(rs.next())
			{
				roomnumber=rs.getInt("maxim")+1;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("newroom", String.valueOf(roomnumber));
		request.setAttribute("floorz", floor);
		Connection con=null;
		PreparedStatement ps=null;
			try  {
			con = dbRes.getConnection();
			ps = con.prepareStatement("Insert into room_hotel values ("+roomnumber+","+floor+",'"+type+"',"+price+","+v1+","+v2+","+v3+","+v4+","+v5+","+v6+","+v7+","+v8+")");
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			finally {
				 try {
					con.close();
					ps.close();	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}	
			this.getServletContext().getRequestDispatcher("/roomconfirmation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
