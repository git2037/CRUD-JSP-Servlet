package config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WebConfig {
	// chứa những quyền và những servletPath của những quyền tương ứng
	private static Map<String, List<String>> role = new HashMap<>();
	
	static {
		// khởi tạo quyền
		init();
	}
	
	public static void init() {
		List<String> roleForStudents = new ArrayList<>();
		List<String> roleForAdmin = new ArrayList<>();
		List<String> roleForGuest = new ArrayList<>();
		
		// thêm servletPath cho quyền là khách
		roleForGuest.add("/");
		roleForGuest.add("/home");
		roleForGuest.add("/login");
		roleForGuest.add("/logout");
		
		// thêm servletPath cho quyền là sinh viên
		roleForStudents.addAll(roleForGuest);
		roleForStudents.add("/student-info");
		roleForStudents.add("/change-password");
		
		// thêm servletPath cho quyền là admin
		roleForAdmin.addAll(roleForGuest);
		roleForAdmin.add("/student-list");
		roleForAdmin.add("/create-student");
		roleForAdmin.add("/edit-student");
		roleForAdmin.add("/delete-student");
		roleForAdmin.add("/search-student");
		
		// 	đẩy quyền và những servletPath tương ứng vào map
		role.put("student", roleForStudents);
		role.put("admin", roleForAdmin);
		role.put("guest", roleForGuest);
		return;
	}
	
	public static Set<String> getALlRole() {
		// lấy ra tất cả các quyền
		return role.keySet();
	}
	
	public static List<String> getAllUrlForRole(String role) {
		// lấy ra tất cả cả servletPath của quyền "role"
		return WebConfig.role.get(role);
	}
}
