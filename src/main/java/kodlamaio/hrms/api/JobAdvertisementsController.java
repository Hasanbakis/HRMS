package kodlamaio.hrms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@RestController
@RequestMapping("/api/jobAdvertisement")
@CrossOrigin
public class JobAdvertisementsController {
	private JobAdvertisementService jobAdvertisementService;

	@Autowired
	public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
		super();
		this.jobAdvertisementService = jobAdvertisementService;
	}
	
	@GetMapping("getall")
	public DataResult<List<JobAdvertisement>>getAll(){
		return this.jobAdvertisementService.getAllStatusTrue();
		
	}
	
	@PostMapping("add")
	public Result add(@RequestBody JobAdvertisement jobAdvertisement) {
		return this.jobAdvertisementService.add(jobAdvertisement);
	}
	
	@GetMapping("getDataBySortedAsc")
	public DataResult<List<JobAdvertisement>> getDataBySortedAsc() {
		return this.jobAdvertisementService.findAllByStatusTrueSortedAsc();
	}
	
	@GetMapping("getAllByEmployerId")
	public DataResult<List<JobAdvertisement>> getByAllEmployerId(int employer_id){
		return this.jobAdvertisementService.findAllByEmployer_IdAndStatusTrue(employer_id);
	}
	
	@PutMapping("update")
	public Result update(@RequestParam int jobPostId,@RequestParam boolean status) {
		return this.jobAdvertisementService.findByIdToChangeStatus(jobPostId, status);
	}
	
	@GetMapping("getById")
	public DataResult<JobAdvertisement> getByAdvertisementId(int id){
		return this.jobAdvertisementService.findByIdAndStatusTrue(id);
	}
	
	
	
	
	
	
	

}
