package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementService {
	
	 DataResult<List<JobAdvertisement>> getAll();
	 DataResult<List<JobAdvertisement>> getAllStatusTrue();
	 DataResult<List<JobAdvertisement>> findAllByEmployer_IdAndStatusTrue(int employerId);
	 
	 Result add(JobAdvertisement jobAdvertisement);
	 Result findByIdToChangeStatus(int id,boolean status);
	 
	  DataResult<List<JobAdvertisement>> findAllByStatusTrueSortedAsc();
	  
	  
		

}
