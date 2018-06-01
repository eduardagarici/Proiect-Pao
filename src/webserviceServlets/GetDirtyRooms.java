package webserviceServlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import core.Rooms;

/**
 * Servlet implementation class GetDirtyRooms
 */
@WebServlet(name = "GetDirtyRooms", urlPatterns = { "/GetDirtyRooms" })
public class GetDirtyRooms extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/myDb")
	private DataSource dbRes;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDirtyRooms() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LinkedList<Rooms> r = new LinkedList<>();
		try (Connection con = dbRes.getConnection();
				PreparedStatement ps = con.prepareStatement("Select * from cleaning_hotel");
				ResultSet rs = ps.executeQuery();) {
			while(rs.next()){
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
		try (Connection con = dbRes.getConnection();
				PreparedStatement ps = con.prepareStatement("Select * from room_hotel where id_room not in (select id_room from cleaning_hotel)");
				ResultSet rs = ps.executeQuery();) {
			while(rs.next()){
				
				Rooms temp = new Rooms();
				Double i1=Math.random()*28+1;
				int a=i1.intValue();
				Double i2=Math.random()*11+1;
				int b=i2.intValue();
				/*Agarici Eduard
				Munteanu Beatrice
				Nastase Stefan  9*/
				
				
				temp.setNrcam(rs.getInt("id_room"));
				temp.setStatus("clean");
				temp.setCldate("Not required");
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}