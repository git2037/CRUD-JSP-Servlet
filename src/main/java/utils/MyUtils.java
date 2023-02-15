package utils;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {
	public static String convertToSQLDate(String date) {
		// dd/mm/yyyy => yyyy/mm/dd
		String[] arr = date.split("/");
		String res = "";
		int size = arr.length;
		for (int i = size - 1; i >= 0; i--) {
			res += arr[i];
			if (i == 0) {
				continue;
			} else {
				res += "-";
			}
		}
		
		return res;
	}
	
	public static String convertToJavaDate(String date) {
		// yyyy-mm-dd => dd/mm/yyyy 
		String[] arr = date.split("-");
		String res = "";
		int size = arr.length;
		for (int i = size - 1; i >= 0; i--) {
			res += arr[i];
			if (i == 0) {
				continue;
			} else {
				res += "/";
			}
		}
		
		return res;
	}
	
	public static int convertBoolenToBit(boolean c) {
		return c ? 1 : 0;
	}
	
	public static int convertStringToBit(String s) {
		return (s.equals("Nam")) ? 1 : 0;
	}
	
	public static boolean convertBitToBoolen(int n) {
		return (n == 1) ? true : false;
	}
	
	public static boolean convertStringToBoolean(String s) {
		return (s.equals("1")) ? true : false;
	}
	
	public static boolean checkDate(String date) {
		// kiểm tra ngày tháng năm được truyền vào
		Pattern pattern = Pattern.compile("^\\d{2}[-|/]\\d{2}[-|/]\\d{4}$");
		Matcher matcher = pattern.matcher(date);
		boolean check = matcher.find();
		if(check) {
			date = convertToSQLDate(date);
			LocalDate today = LocalDate.now();
			LocalDate d = LocalDate.parse(date);
			
			if (today.compareTo(d) > 0) {
				return true;
			} else {
				return false;
			}
		}
		return check;
	}
	
	public static boolean checkNullCreate(String id, String fullName, String dob, String gender, String address, String password) {
		if (id.equals("") || id.equals("") || dob.equals("") || gender.equals("") || address.equals("") || password.equals("")) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean checkNullEdit(String id, String fullName, String dob, String gender, String address) {
		if (id.equals("") || id.equals("") || dob.equals("") || gender.equals("") || address.equals("")) {
			return false;
		} else {
			return true;
		}
	}
}
