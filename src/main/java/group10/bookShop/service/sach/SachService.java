package group10.bookShop.service.sach;

import java.util.List;

import group10.bookShop.entities.Sach;

public interface SachService 
{
	Iterable<Sach> findAll();
	
	List<Sach> search(String tensach);
	
	Sach findById(Integer masach);
	
	void save(Sach sach);
	
	void delete(Integer masach);
}
