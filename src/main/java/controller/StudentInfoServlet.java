package controller;

import java.io.IOException;

import dao.StudentDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Student;

@WebServlet(urlPatterns = "/student-info")
public class StudentInfoServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy ra userName trong session
		HttpSession hs = req.getSession();
		String id = (String) hs.getAttribute("loginedUser");
		
		// tìm kiếm sinh viên có userName vừa lấy ra
		Student sv = StudentDAO.getInstance().find(id);
		
		// lưu sinh viên vào biến
		req.setAttribute("AcctuallyStudent", sv);
		
		// chuyển hướng qua view
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/studentInfoView.jsp");
		rd.forward(req, resp);
		return;
	}

}
