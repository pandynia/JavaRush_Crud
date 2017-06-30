package org.pandynia.javarush.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.spr.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{
	@Query("select b from Users b where b.name like ?1%")
	List<Users> findName(String name);

}
