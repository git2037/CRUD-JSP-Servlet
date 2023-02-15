package controller;

import java.io.IOException;
import dao.AccountDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

@WebServlet(urlPatterns = "/change-password")
public class ChangePasswordServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/changePasswordView.jsp");
		rd.forward(req, resp);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy tham số trong session
		HttpSession hs = req.getSession();
		String userName = (String) hs.getAttribute("loginedUser");
		
		// tìm account
		Account acc = AccountDAO.getInstance().find(userName);
		
		// lấy tham số trong form
		String oldPassword = req.getParameter("oldPassword");
		String newPassword = req.getParameter("newPassword");
		String duplicatePassword = req.getParameter("duplicatePassword");

		if (newPassword.equals(duplicatePassword)) { // mật khẩu mới trùng mật khẩu được gõ lại
			if (oldPassword.equals(acc.getPassword())) { // mật khẩu cũ đúng
				Account account = new Account(userName, newPassword);
				boolean check = AccountDAO.getInstance().edit(account); // thay đổi password
				
				if (check) { // thay đổi được mật khẩu
					// chuyển về trang /student-info
					resp.sendRedirect(req.getContextPath() + "/student-info");
					return;
				} else {
					// có lỗi chuyển hướng đến WEB-INF/view/changePasswordView.jsp
					req.setAttribute("error", "Đã xảy ra lỗi!");
					RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/changePasswordView.jsp");
					rd.forward(req, resp);
					return;
				}
				
			} else { // mật khẩu cũ sai
				req.setAttribute("error", "Mật khẩu cũ không đúng!");
				RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/changePasswordView.jsp");
				rd.forward(req, resp);
				return;
			}
		} else { // mật khẩu mới không giống mật kâur gõ lại
			req.setAttribute("error", "Mật khẩu mới không khớp với mật khẩu được gõ lại!");
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/changePasswordView.jsp");
			rd.forward(req, resp);
			return;
		}
	}

}
