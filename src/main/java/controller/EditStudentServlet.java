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

@WebServlet(urlPatterns = "/edit-student")
public class EditStudentServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy ra tham số trong form
		String id = req.getParameter("code");
		Student sv = StudentDAO.getInstance().find(id);

		if (sv != null) {
			req.setAttribute("selectedStudent", sv);
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/editStudentView.jsp");
			rd.forward(req, resp);
			return;
		} else {
			resp.sendRedirect(req.getContextPath() + "/student-list");
			return;
		}	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy dữ liệu từ form
		String id = req.getParameter("code");
		String name = req.getParameter("fullName");
		String dob = req.getParameter("dob");
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		String password = req.getParameter("resetPassword");
		
		if (MyUtils.checkNullEdit(id, name, dob, gender, address)) {
			if (MyUtils.checkDate(dob)) {	
				Student sv = new Student(id, name, MyUtils.convertToSQLDate(dob), MyUtils.convertStringToBoolean(gender), address);				
				
				// chỉnh sửa
				boolean check = StudentDAO.getInstance().edit(sv); // có chỉnh sửa được thông tin không			
				boolean c = false; // kiểm tra lỗi
				
				if (!password.equals("1")) { 
					// không reset password 
					c = check; 
				} else { // reset password
					Account account = new Account(id, "123456");
					boolean check2 = AccountDAO.getInstance().edit(account); // có chỉnh sửa password không
					
					if (check && check2) {
						c = true;
					} 
				}
				
				if (c) { // không lỗi
					resp.sendRedirect(req.getContextPath() + "/student-list");
					return;
				}		
			}
		} else { // có trường rỗng
			String error = "Sửa thông tin thất bại. Hãy thử lại!";
			req.setAttribute("error", error);
			Student sv1 = new Student(id, name, dob, MyUtils.convertStringToBoolean(gender), address);
			System.out.println(sv1.getStudentID() + "," + sv1.getDob());
			req.setAttribute("selectedStudent", sv1);
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/editStudentView.jsp");
			rd.forward(req, resp);
			return;
		}
		
	}

}
