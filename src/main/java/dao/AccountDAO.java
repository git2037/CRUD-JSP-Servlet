package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.JDBCConnection;
import model.Account;

public class AccountDAO implements DAO<Account> {

	public static AccountDAO getInstance() {
		// trả về 1 instance
		return new AccountDAO();
	}

	@Override
	public List<Account> getAll() {
		Connection con = null;

		try {
			con = JDBCConnection.getJDBCConnection();
			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement("select * from account");
			ResultSet rs = ps.executeQuery();
			List<Account> accountList = new ArrayList<>();

			while (rs.next()) {
				String userName = rs.getString("userName");
				String password = rs.getString("password");

				accountList.add(new Account(userName, password));
			}
			
			JDBCConnection.closeConnection(con);
			return accountList;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Account find(String id) {
		Connection con = null;

		try {
			con = JDBCConnection.getJDBCConnection();
			PreparedStatement ps = con.prepareStatement("select * from account where userName = ?",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();

			Account account = null;
			while (rs.next()) {
				String password = rs.getString("password");
				account = new Account(id, password);
			}

			JDBCConnection.closeConnection(con);
			return account;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean insert(Account t) {
		Connection con = null;
		
		try {
			con = JDBCConnection.getJDBCConnection();
			con.setAutoCommit(false);
			String sql = "insert into account values (?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, t.getUserName());
			ps.setString(2, t.getPassword());

			int row = ps.executeUpdate();

			if (row == 1) {
				con.commit();
				JDBCConnection.closeConnection(con);
				return true;
			}
			
			JDBCConnection.closeConnection(con);
			return false;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean edit(Account t) {
		Connection con = null;
		
		try {
			con = JDBCConnection.getJDBCConnection();
			con.setAutoCommit(false);
			String sql = "update account set password = ? where userName = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, t.getPassword());
			ps.setString(2, t.getUserName());

			int row = ps.executeUpdate();

			if (row == 1) {
				con.commit();
				JDBCConnection.closeConnection(con);
				return true;
			}
			
			JDBCConnection.closeConnection(con);
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(String id) {
		Connection con = null;
		
		try {
			con = JDBCConnection.getJDBCConnection();
			con.setAutoCommit(false);
			String sql = "delete from account where userName = ?";
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
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}	
	}

}
