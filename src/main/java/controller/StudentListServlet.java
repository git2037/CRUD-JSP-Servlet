package controller;

import java.io.IOException;
import java.util.List;

import dao.StudentDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;

@WebServlet(urlPatterns = "/student-list")
public class StudentListServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy tất cả sinh viên trong database
		List<Student> list = StudentDAO.getInstance().getAll();
		// lưu danh sách sinh viên vào biến trước khi chuyển hướng
		req.setAttribute("studentList", list);
		// chuyển hướng
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/studentListView.jsp");
		rd.forward(req, resp);
		return;	
	}
}
