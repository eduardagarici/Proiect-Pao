package presentationServlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import core.RoomStatus;

import java.util.List;
/**
 * Servlet implementation class CheckRoomStatus
 */
@WebServlet("/CheckRoomStatus")
public class CheckRoomStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/myDb")
	private DataSource dbRes; 
    private List<RoomStatus> roomStatus = new LinkedList<>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckRoomStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws SQLException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    public void SearchRoomStatus(Connection con,String dateInputString,String roomId) throws SQLException
	   {
		String status1="";
		String status2="";
      try( PreparedStatement ps=con.prepareStatement("SELECT id_room from reservation_hotel where id_room= "+roomId+
			                                     "and checkin<="  + dateInputString + "and checkout>="+dateInputString);
             ResultSet rs=ps.executeQuery())
        {
          if(rs.next())
          {
           status1 = "reserved";
           status2="dirty";
          }
         else
         {
          status1="free";
		  
		  try(PreparedStatement ps2=con.prepareStatement("SELECT status from cleaning_hotel where id_room= "+roomId+
		    			                                     "and cleaning_date="+ dateInputString);
				  ResultSet rs2=ps2.executeQuery())	  
		   {
			  
		   if(rs2.next())
		   { 
			   status2=rs2.getString("status");
			   System.out.println(status2);
		   }
		   else
		   {
			   status2="cleaned";
			  
		   }
		   }
           }
          
          roomStatus.add(new RoomStatus(roomId,status1,status2));
         }
		
      
 }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate=null;
		String dateInput=null;
		String roomId = null;
		boolean isDateSet = true;
		boolean isRoomSet = true;
		if (request.getParameter("date") == "") { 
		     currentDate =format.format(Calendar.getInstance().getTime());
		     isDateSet=false;
		}  
		if (request.getParameter("noRoom") == "") { 
		     isRoomSet=false;
		}
		else 
			roomId = request.getParameter("noRoom");
		
		try {
		     if(isDateSet)
		         dateInput = request.getParameter("date");
		              
		     else 
		    	 dateInput = currentDate;
			 
		     
		    String dateInputString="to_date('"+dateInput+"','yyyy-mm-dd') ";
		    
	        try(Connection con=dbRes.getConnection())
	        { 
	          if(isRoomSet) 	
	        
	            try(PreparedStatement ps3=con.prepareStatement("SELECT id_room from room_hotel where id_room= "+roomId);
	        	    	  ResultSet rs3=ps3.executeQuery())
	           {		
	  	    	 if(!rs3.next()) 
	  	         {
	  	    	    request.setAttribute("invalid","Invalid room number");
	  	   		    request.getServletContext().getRequestDispatcher("/CheckRoomStatus.jsp").forward(request, response);
	   			    return;
	   		     }
	  	    	 else
	  	    		SearchRoomStatus(con,dateInputString,roomId);
		    	}
		     else
		     {
		    	 try( PreparedStatement ps=con.prepareStatement("SELECT id_room from room_hotel");
				          ResultSet rs=ps.executeQuery())
		    	 {
		    		 while(rs.next())
		    		 {
		    			 roomId=rs.getString("id_room");
		    			 SearchRoomStatus(con,dateInputString,roomId);
		    		 }
		    	 }
		     }
	         
		    }
	        request.setAttribute("date",dateInput);
	        request.setAttribute("rooms",roomStatus);
		    request.getServletContext().getRequestDispatcher("/CheckRoomStatus.jsp").forward(request, response);
		    roomStatus.clear();
	        return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
