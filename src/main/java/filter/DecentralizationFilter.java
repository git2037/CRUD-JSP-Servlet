package filter;

import java.io.IOException;

import config.PermissionConfig;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter(filterName = "decentralizationFilter", urlPatterns = {"/*"})

//phân quyền
public class DecentralizationFilter implements Filter{ 

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;		
		boolean checkPer = PermissionConfig.hasPermission(req); // request này có quyền truy cập không
		
		if (checkPer) { // có quyền
			arg2.doFilter(arg0, arg1); 
			return;
		} else { // không có quyền
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/accessDeniedView.jsp");
			rd.forward(arg0, arg1);
			return;
		}
		
	}

}
