package webserviceServlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import core.Rooms;

/**
 * Servlet implementation class Room
 */
@WebServlet(name = "RoomClean", urlPatterns = { "/RoomClean" })
public class RoomClean extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/myDb")
	private DataSource dbRes;

	/**
	 * Default constructor.
	 */
	public RoomClean() {
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String room = request.getParameter("room");
		String cal = request.getParameter("calendar");
//		Date date=new Date(cal);
//		SimpleDateFormat ff=new SimpleDateFormat("dd-mm-yyyy");
//		String fina=ff.format(date);
//		System.out.println(fina);
		LinkedList<Rooms> r = new LinkedList<>();
		try (Connection con = dbRes.getConnection();
				PreparedStatement ps = con.prepareStatement("Select * from cleaning_hotel where id_room=" + room);
				ResultSet rs = ps.executeQuery();) {
			if (!rs.next()) {
				Rooms temp = new Rooms();
				temp.setNrcam(Integer.parseInt(room));
				temp.setStatus("clean");
				temp.setCldate(cal);
				r.add(temp);
			} else {
				Rooms temp = new Rooms();
				temp.setNrcam(rs.getInt("id_room"));
				temp.setStatus(rs.getString(2));
				temp.setCldate(rs.getString(3));
				r.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("camere", r);
		this.getServletContext().getRequestDispatcher("/roompreview.jsp").forward(request, response);
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
