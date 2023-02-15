package controller;

import java.io.IOException;
import dao.AccountDAO;
import dao.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/delete-student")
public class DeleteStudentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("code");
		boolean check2 = AccountDAO.getInstance().delete(id); // xoá tài khoản
		boolean check = StudentDAO.getInstance().delete(id); // xoá thông tin sinh viên
		
		if (check && check2) {
			resp.sendRedirect(req.getContextPath() + "/student-list");
			return;
		} else {
			req.setAttribute("error", "Xảy ra lỗi. Vui lòng thử lại!");
			resp.sendRedirect(req.getContextPath() + "/student-list");
			return;
		}	
	}
}
