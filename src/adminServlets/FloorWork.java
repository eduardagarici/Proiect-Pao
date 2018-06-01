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
 * Servlet implementation class FloorWork
 */
@WebServlet(name = "FloorWork", urlPatterns = { "/FloorWork" })
public class FloorWork extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/myDb")
	private DataSource dbRes;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FloorWork() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String floor = request.getParameter("newfloor");
		String rrooms = request.getParameter("numberatfloor");
		String type = request.getParameter("typesfloor");
		String price = request.getParameter("pricefloor");
		String v1 = request.getParameter("pref11");
		String v2 = request.getParameter("pref22");
		String v3 = request.getParameter("pref33");
		String v4 = request.getParameter("pref44");
		String v5 = request.getParameter("pref55");
		String v6 = request.getParameter("pref66");
		String v7 = request.getParameter("pref77");
		String v8 = request.getParameter("pref88");
		int roomnumber = 0;
		int countrooms = Integer.parseInt(rrooms);
		request.setAttribute("newfloor", floor);
		request.setAttribute("numberofrooms", String.valueOf(countrooms));
		try (Connection con = dbRes.getConnection();
				PreparedStatement ps = con.prepareStatement("Select max(id_room) maxim from room_hotel");
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				roomnumber = rs.getInt("maxim") + 1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con = dbRes.getConnection();
			while (countrooms != 0) {
				ps = con.prepareStatement("Insert into room_hotel values (" + roomnumber + "," + floor
						+ ",'" + type + "'," + price + "," + v1 + "," + v2 + "," + v3 + "," + v4 + "," + v5 + "," + v6
						+ "," + v7 + "," + v8 + ")");
				ps.executeUpdate();
				countrooms--;
				roomnumber++;
			}
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

		this.getServletContext().getRequestDispatcher("/flooraddition.jsp").forward(request, response);
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
