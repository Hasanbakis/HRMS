package kodlamaio.hrms.dataAccess;



import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.Candidate;



public interface CandidateDoa extends JpaRepository<Candidate , Integer>{   
	
}
