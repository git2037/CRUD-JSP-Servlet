package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(filterName = "accessFilter", urlPatterns = "/*")

// quyền truy cập
public class AccessFilter implements Filter{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpSession hs = req.getSession();
		String userName = (String) hs.getAttribute("loginedUser"); // lấy dữ liệu từ session
		String servletPath = req.getServletPath(); // lấy servletPath
		
		if (userName != null || servletPath.equals("/login") || servletPath.equals("/home") || servletPath.equals("/")) { // các trường hợp được đi qua filter
			arg2.doFilter(arg0, arg1);
			return;
		} else { // không thuộc các trường hợp trên chuyển sang trang login	
			req.setAttribute("servletPath", servletPath);
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/loginView.jsp");
			rd.forward(arg0, arg1);
			return;
		}
	}

}
