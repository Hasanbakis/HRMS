package kodlamaio.hrms.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import kodlamaio.hrms.entities.concretes.JobAdvertisement;


public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer>{
	
	List<JobAdvertisement>findAllByStatusTrue();
	
	List<JobAdvertisement>findAllByStatusTrueOrderByReleaseDateAsc();
	

	List<JobAdvertisement>findAllByEmployer_IdAndStatusTrue(int employerId);
	

	

}
