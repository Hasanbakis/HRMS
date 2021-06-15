package kodlamaio.hrms.dataAccess;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.Education;



public interface EducationDao extends JpaRepository<Education, Integer>{
	
	
}
