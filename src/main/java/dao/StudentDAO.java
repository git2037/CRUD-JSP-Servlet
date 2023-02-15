package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.JDBCConnection;
import model.Student;
import utils.MyUtils;

public class StudentDAO implements DAO<Student> {

	public static StudentDAO getInstance() {
		// trả về 1 instance 
		return new StudentDAO();
	}

	@Override
	public List<Student> getAll() {
		Connection con = null;

		try {
			con = JDBCConnection.getJDBCConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement("select * from student");
			ResultSet rs = ps.executeQuery();
			List<Student> studentsList = new ArrayList<>();

			while (rs.next()) {
				String id = rs.getString("studentID");
				String name = rs.getString("fullName");
				Date date = rs.getDate("dob");
				String dob = MyUtils.convertToJavaDate(date.toString());
				Boolean gender = true;
				gender = MyUtils.convertBitToBoolen(rs.getInt("gender"));
				String address = rs.getString("address");

				studentsList.add(new Student(id, name, dob, gender, address));
			}

			JDBCConnection.closeConnection(con);
			return studentsList;
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}

	@Override
	public Student find(String id) {
		Connection con = null;

		try {
			con = JDBCConnection.getJDBCConnection();
			PreparedStatement ps = con.prepareStatement("select * from student where studentID = ?",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();

			Student sv = null;
			while (rs.next()) {
				String name = rs.getString("fullName");
				String dob = rs.getString("dob");
				boolean gender = MyUtils.convertBitToBoolen(rs.getInt("gender"));
				String address = rs.getString("address");
				sv = new Student(id, name, dob, gender, address);
			}

			JDBCConnection.closeConnection(con);
			return sv;
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean insert(Student t) {
		Connection con = null;

		try {
			con = JDBCConnection.getJDBCConnection();
			con.setAutoCommit(false);
			String sql = "insert into student values (?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, t.getStudentID());
			ps.setString(2, t.getFullName());
			ps.setString(3, MyUtils.convertToSQLDate(t.getDob()));
			ps.setInt(4, MyUtils.convertBoolenToBit(t.getGender()));
			ps.setString(5, t.getAddress());

			int row = ps.executeUpdate();

			if (row == 1) {
				con.commit();
				JDBCConnection.closeConnection(con);
				return true;
			}

			JDBCConnection.closeConnection(con);
			return false;
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean edit(Student t) {
		Connection con = null;

		try {
			con = JDBCConnection.getJDBCConnection();
			con.setAutoCommit(false);
			String sql = "update student set fullName = ?, dob = ?, gender = ?, address = ? where studentID = ?";
			PreparedStatement ps;
			ps = con.prepareStatement(sql);
			ps.setString(1, t.getFullName());
			ps.setString(2, MyUtils.convertToSQLDate(t.getDob()));
			ps.setInt(3, MyUtils.convertBoolenToBit(t.getGender()));
			ps.setString(4, t.getAddress());
			ps.setString(5, t.getStudentID());

			int row = ps.executeUpdate();

			if (row == 1) {
				con.commit();
				JDBCConnection.closeConnection(con);
				return true;
			}

			JDBCConnection.closeConnection(con);
			return false;
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(String id) {
		Connection con = null;

		try {
			con = JDBCConnection.getJDBCConnection();
			con.setAutoCommit(false);
			String sql = "delete from student where studentID = ?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, id);

			int row = ps.executeUpdate();

			if (row == 1) {
				con.commit();
				JDBCConnection.closeConnection(con);
				return true;
			}

			JDBCConnection.closeConnection(con);
			return false;
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}

}
