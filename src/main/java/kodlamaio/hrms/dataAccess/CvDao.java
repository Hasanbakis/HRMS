package kodlamaio.hrms.dataAccess;



import org.springframework.data.jpa.repository.JpaRepository;


import kodlamaio.hrms.entities.concretes.Cv;

public interface CvDao extends JpaRepository<Cv , Integer>{   
	 Cv findByCandidateId(int id);
	 
}