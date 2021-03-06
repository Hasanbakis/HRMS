package kodlamaio.hrms.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.ImageService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Image;


@RestController
@RequestMapping("/api/images")
@CrossOrigin
public class ImagesController {
	private ImageService imageService;
	private CandidateService candidateService;

	
	@Autowired
	public ImagesController(ImageService imageService,CandidateService candidateService) {
		super();
		this.imageService = imageService;
		this.candidateService=candidateService;

	}
	
	@PostMapping("/add")
	public Result add(@RequestParam(value="id")int id,@RequestParam(value="imageFile")MultipartFile imageFile) {
			Candidate candidate=this.candidateService.getById(id).getData();
			Image image =new Image();
			
			return this.imageService.add(image, imageFile);
	}

	
	
	

}
