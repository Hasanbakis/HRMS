package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.EmployerDao;
import kodlamaio.hrms.dataAccess.JobAdvertisementDao;

import kodlamaio.hrms.entities.concretes.JobAdvertisement;


@Service
public class JobAdvertisementManager implements JobAdvertisementService{

	private JobAdvertisementDao jobAdvertisementDao;
	private EmployerDao employerDao;
	
	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll(),"data listed");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllStatusTrue() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAllByStatusTrue());
	}

	@Override
	public Result add(JobAdvertisement jobAdvertisement) {
	   this.jobAdvertisementDao.save(jobAdvertisement);
	   return new SuccessResult("Job posting successfully added");
	}

	@Override
	public DataResult<List<JobAdvertisement>> findAllByStatusTrueSortedAsc() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAllByStatusTrueOrderByReleaseDateAsc(),
				"Active Data Listed by Date");
	}

	@Override
	public Result findByIdToChangeStatus(int id, boolean status) {
		try {
			JobAdvertisement jobAdvertisement;
			jobAdvertisement=this.jobAdvertisementDao.findById(id).get();
			
			jobAdvertisement.setStatus(status);
			
			this.jobAdvertisementDao.save(jobAdvertisement);
			
			return new SuccessResult("Status successfully changed");
		}catch(Exception e) {
			return new ErrorResult("Status could not be changed");
		}
		
	}

	@Override
	public DataResult<List<JobAdvertisement>> findAllByEmployer_IdAndStatusTrue(int employerId) {
		 
		return new SuccessDataResult<List<JobAdvertisement>>
		(this.jobAdvertisementDao.findAllByEmployer_IdAndStatusTrue(employerId),
				"Active job postings of the company are listed");
	}


	
	

}
