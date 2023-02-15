package controller;

import java.io.IOException;
import dao.AccountDAO;
import dao.StudentDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.Student;
import utils.MyUtils;

@WebServlet(urlPatterns = "/create-student")
public class CreateStudentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/createStudentView.jsp");
		rd.forward(req, resp);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy các tham số
		String id = req.getParameter("studentID");
		String name = req.getParameter("fullName");
		String dob = req.getParameter("dob");
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		String password = req.getParameter("password");

		if (MyUtils.checkNullCreate(id, name, dob, gender, address, password)) {
			if (MyUtils.checkDate(dob)) {
				Student sv = new Student(id, name, MyUtils.convertToSQLDate(dob),
						MyUtils.convertStringToBoolean(gender), address);
				Account account = new Account(id, password);

				boolean check = StudentDAO.getInstance().insert(sv); // thêm sinh viên
				boolean check2 = AccountDAO.getInstance().insert(account); // thêm tài khoản

				if (check && check2) {
					resp.sendRedirect(req.getContextPath() + "/student-list");
					return;
				}
			} else { // có trường rỗng
				req.setAttribute("error", "Xảy ra lỗi. Vui lòng thử lại!");
				RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/createStudentView.jsp");
				rd.forward(req, resp);
				return;
			}
		}
	}
}
