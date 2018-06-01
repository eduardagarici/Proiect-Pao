package presentationServlets;

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
 * Servlet implementation class Reserve
 */
@WebServlet(name = "Reserve", urlPatterns = {"/Reserve"})
public class Reserve extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/myDb")
	private DataSource dbRes; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reserve() {
    }
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id_user=(Integer)request.getSession().getAttribute("id_user");
		
		if(id_user==null)
		{
			request.setAttribute("source", "reserve");
			request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		System.out.println(request.getParameter("checkin"));
		Integer id_room=Integer.parseInt(request.getParameter("reserve"));
		String checkin="to_date('"+request.getParameter("checkin") + "','yyyy-mm-dd')" ;
		String checkout="to_date('"+request.getParameter("checkout") + "','yyyy-mm-dd')";
		String sql= "select id_room from reservation_hotel where id_room=" + id_room 
				   + " and ((checkin<" + checkin+ " and checkout>" + checkout+") or " 
				   + "(checkin>" + checkin + " and checkout>" + checkout+") or "
				   + "(checkin>" + checkin + " and checkin<" +  checkout+") or "
				   + "(checkout>" + checkin + " and checkout<"+ checkout+"))";
		try(Connection con=dbRes.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery()
			)
		{
			if(rs.next())
			{
				request.setAttribute("alreadyReserved", "true");
				request.getServletContext().getRequestDispatcher("/PresentationPage.jsp").forward(request, response);
				return;
			}
			try(PreparedStatement ps2=con.prepareStatement("Select Max(id_reservation) from reservation_hotel");
				ResultSet rs2=ps2.executeQuery()){
				int nr=1;
				rs2.next();
				nr+=rs2.getInt(1);
				try(PreparedStatement ps3=con.prepareStatement("Insert into "
						+ "reservation_hotel values(?,?,?,"+checkin+","+checkout+")"))
				{
					ps3.setInt(1,nr);
					ps3.setInt(2,id_room);
					ps3.setInt(3, id_user);
					if(ps3.executeUpdate()!=1)
					{
						request.setAttribute("reserveError", "error");
						request.getServletContext().getRequestDispatcher("/PresentationPage.jsp").forward(request, response);
						return;
					}
					request.setAttribute("reservationComplete", "complete");
					request.getServletContext().getRequestDispatcher("/MainPage.jsp").forward(request, response);
				}	
			}
		} 
		catch (SQLException e) {
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

}
