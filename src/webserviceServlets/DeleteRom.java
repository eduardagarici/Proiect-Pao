package webserviceServlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class DeleteRom
 */
@WebServlet(name = "DeleteRom", urlPatterns = { "/DeleteRom" })
public class DeleteRom extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/myDb")
	private DataSource dbRes;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteRom() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String a = request.getParameter("numar");
		String b = request.getParameter("status");
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con = dbRes.getConnection();
			ps = con.prepareStatement("Delete from cleaning_hotel where id_room=" + a);
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
		// this.getServletContext().getRequestDispatcher("/index.jsp").forward(request,
		// response);
		request.setAttribute("curatat", a);
		this.getServletContext().getRequestDispatcher("/confirmation.jsp").forward(request, response);
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
