package config;

import java.util.List;
import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class PermissionConfig {

	public static boolean hasPermission(HttpServletRequest req) {
		// kiểm tra người dùng vừa đăng nhập có quyền gì
		HttpSession hs = req.getSession();
		String userName = (String) hs.getAttribute("loginedUser");
		String role = null;

		if (userName == null) {
			// người dùng có quyền là khách
			role = "guest";
		} else if (userName.equals("admin")) {
			// người dùng có quyền là admin
			role = "admin";
		} else if (userName.startsWith("sv")) {
			// người dùng có quyền là sinh viên
			role = "student";
		} else {
			// người dùng có quyền khác
			return false;
		}

		// lấy ra các quyền
		Set<String> allRole = WebConfig.getALlRole();
		
		if (allRole.contains(role)) { // quyền của người dùng có trong webapp
			// lấy ra các servletPath của quyền "role"
			List<String> allUrl = WebConfig.getAllUrlForRole(role);

			String servletPath = req.getServletPath();

			if (allUrl.contains(servletPath)) { // người dùng có quyền để truy cập
				return true;
			} else { // không có quyền
				return false;
			}
		} else { // quyền của người dùng không có trong webapp
			return false;
		}

	}
}
