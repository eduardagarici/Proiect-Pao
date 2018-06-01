package presentationServlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import core.Room;

/**
 * Servlet implementation class MainPage
 */
@WebServlet(name = "Presentation", urlPatterns = {"/Presentation"})
public class PresentationPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/myDb")
	private DataSource dbRes; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PresentationPage() {
    }
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String checkin=request.getParameter("checkin");
		String checkout=request.getParameter("checkout");
		String roomType=request.getParameter("roomType");
		String WIFI=request.getParameter("WIFI");
		String TV=request.getParameter("TV");
		String AC=request.getParameter("AC");
		String minibar=request.getParameter("minibar");
		String roomService=request.getParameter("roomService");
		String seaview=request.getParameter("seaview");
		String parkingSpot=request.getParameter("parkingSpot");
		String balcony=request.getParameter("balcony");
		String priceJsp=request.getParameter("price");
		
		if(checkin==null || checkin.equals("")) {
			request.setAttribute("checkin", "failed");
			request.getServletContext().getRequestDispatcher("/PresentationPage.jsp").forward(request, response);
			return;
		}
		if(checkout==null || checkout.equals("")) {
			request.setAttribute("checkout", "failed");
			request.getServletContext().getRequestDispatcher("/PresentationPage.jsp").forward(request, response);
			return;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
				Date checkIn=sdf.parse(checkin);
				Date checkOut=sdf.parse(checkout);
				sdf.applyPattern("MM-dd-yyyy");
				if(checkIn.after(checkOut)) {
					request.setAttribute("dates", "failed");
					request.getServletContext().getRequestDispatcher("/PresentationPage.jsp").forward(request, response);
					return;
				}
				sdf.applyPattern("yyyy-MM-dd");
				request.setAttribute("checkin",sdf.format(checkIn));
				request.setAttribute("checkout",sdf.format(checkOut));
			} 
			catch (ParseException e1) {
				request.setAttribute("search", "failed");
				request.getServletContext().getRequestDispatcher("/PresentationPage.jsp").forward(request, response);
				return;
			}
		List<Room> allRooms=new LinkedList<>();
		String checkinString="to_date('" + checkin +"','yyyy-mm-dd') ";
		String checkoutString="to_date('"+ checkout+"','yyyy-mm-dd') ";
		String sql=""
				   + "select id_room from room_hotel "
				   + "where type='" + roomType +"' MINUS "
				   + "select id_room from reservation_hotel where "
				   + "(checkin<=" + checkinString + "and checkout>=" + checkoutString+") or " 
				   + "(checkin>=" + checkinString + "and checkout>=" + checkoutString+") or "
				   + "(checkin>=" + checkinString + "and checkin<=" +  checkoutString+") or "
				   + "(checkout>=" + checkinString + "and checkout<="+ checkoutString+") MINUS "
				   + "select id_room from cleaning_hotel where "
				   + "cleaning_date=" + checkinString + "and status='dirty'";
		try(Connection con=dbRes.getConnection();
			PreparedStatement ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery())
			{
				List<Integer> idRooms=new ArrayList<>();
				while(rs.next()) {
					idRooms.add(rs.getInt("id_room"));
				}
				if(idRooms.size()==0) {
					request.setAttribute("no_rooms", "failed");
					request.getServletContext().getRequestDispatcher("/PresentationPage.jsp").forward(request, response);
					return;
				}
				StringBuffer s=new StringBuffer("(");
				for(int i=0;i<idRooms.size()-1;i++)
					s.append(idRooms.get(i)+",");
				s.append(idRooms.get(idRooms.size()-1)+")");
				try(PreparedStatement ps2=con.prepareStatement(""
					+ "select * from room_hotel where id_room in " + s.toString());
					ResultSet rs2=ps2.executeQuery()){
					int id_room;
					int floor;
					String type;
					int price;
					while(rs2.next()){
						id_room=rs2.getInt("id_room");
						floor=rs2.getInt("floor");
						type=rs2.getString("type");
						price=rs2.getInt("price");
						Room r=new Room(id_room,floor,type,price);
						r.setAc(rs2.getBoolean("AC"));
						r.setTv(rs2.getBoolean("TV"));
						r.setBalcony(rs2.getBoolean("balcony"));
						r.setMinibar(rs2.getBoolean("minibar"));
						r.setRoom_service(rs2.getBoolean("room_service"));
						r.setWifi(rs2.getBoolean("WIFI"));
						r.setParking(rs2.getBoolean("parking"));
						r.setSeaview(rs2.getBoolean("seaview"));
						allRooms.add(r);
					}
					for(Iterator<Room> iter=allRooms.iterator();iter.hasNext();) {
						Room r=iter.next();
						if(WIFI!=null && r.isWifi()==false)
						{
							iter.remove();
							continue;
						}
						if(AC!=null && r.isAc()==false)
						{
							iter.remove();
							continue;
						}
						if(minibar!=null && r.isMinibar()==false)
						{
							iter.remove();
							continue;
						}
						if(balcony!=null && r.isBalcony()==false)
						{
							iter.remove();
							continue;
						}
						if(parkingSpot!=null && r.isParking()==false)
						{
							iter.remove();
							continue;
						}
						if(TV!=null && r.isTv()==false)
						{
							iter.remove();
							continue;
						}
						if(seaview!=null && r.isSeaview()==false)
						{
							iter.remove();
							continue;
						}
						if(roomService!=null && r.isRoom_service()==false)
						{
							iter.remove();
							continue;
						}
						if(priceJsp!=null && r.getPrice()>Integer.parseInt(priceJsp))
						{
							iter.remove();
							continue;
						}
					}
					
					if(WIFI!=null)request.setAttribute("WIFI", "1");
					if(AC!=null) request.setAttribute("AC", "1");
					if(TV!=null) request.setAttribute("TV", "1");
					if(parkingSpot!=null) request.setAttribute("parkingSpot", "1");
					if(minibar!=null) request.setAttribute("minibar", "1");
					if(roomService!=null) request.setAttribute("roomService", "1");
					if(balcony!=null)request.setAttribute("balcony", "1");
					if(seaview!=null)request.setAttribute("seaview", "1");
					if(priceJsp!=null)request.setAttribute("price", priceJsp);
					request.setAttribute("roomType", roomType);
					request.setAttribute("rooms",allRooms);
					request.getServletContext().getRequestDispatcher("/PresentationPage.jsp").forward(request, response);
				}
				catch (SQLException e) {
					request.setAttribute("search", "failed");
					request.getServletContext().getRequestDispatcher("/PresentationPage.jsp").forward(request, response);
				} 	
			} 
			catch (SQLException e) {
				e.printStackTrace();
				
				request.setAttribute("search", "failed");
				request.getServletContext().getRequestDispatcher("/PresentationPage.jsp").forward(request, response);
			}
	} 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
