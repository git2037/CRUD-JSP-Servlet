package dao;

import java.util.List;

public interface DAO<T> {

	List<T> getAll(); // lấy danh sách T

	T find(String id); // tìm kiếm T theo "id"

	boolean insert(T t); // thêm t

	boolean edit(T t); // sửa t

	boolean delete(String id); // xoá theo "id"
}
