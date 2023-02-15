package controller;

import java.io.IOException;
import dao.StudentDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;

@WebServlet(urlPatterns = "/search-student")
public class SearchStudentServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy ra parameter đã truyền trong form 
		String id = req.getParameter("searchStudent");
		
		// tìm kiếm sinh viên có studentID = id
		Student s = StudentDAO.getInstance().find(id);
		
		if (s != null) { // sinh viên có tồn tại
			// lưu sinh viên vào biến
			req.setAttribute("student", s);
		} else { // sinh viên không tồn tại
			// lưu lỗi để hiển thị
			String error = "Không tìm thấy sinh viên có mã " + id + "!";
			req.setAttribute("error", error);
		}
		
		// chuyển hướng
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/searchStudentView.jsp");
		rd.forward(req, resp);
		return;		
	}

}
