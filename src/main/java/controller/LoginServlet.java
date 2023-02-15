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

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// chuyển hướng
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/loginView.jsp");
		rd.forward(req, resp);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy ra các tham số ở form
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String path = req.getParameter("servletPath");

		if ((userName.equals("admin") && password.equals("admin")) || AccountDAO.getInstance().find(userName) != null) {
			// lưu userName vào session
			HttpSession hs = req.getSession();
			hs.setAttribute("loginedUser", userName);

			
			if (!path.equals("")) { // chuyển hướng
				resp.sendRedirect(req.getContextPath() + path);
			} else { // chuyển hướng
				resp.sendRedirect(req.getContextPath() + "/home");
			}
			return;
		} else {
			String error = "Sai tên đăng nhập hoặc mật khẩu. Hãy thử lại!";
			req.setAttribute("error", error);
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/loginView.jsp");
			rd.forward(req, resp);
			return;
		}
	}
}
