package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.CvService;
import kodlamaio.hrms.business.abstracts.EducationService;
import kodlamaio.hrms.business.abstracts.ExperienceService;
import kodlamaio.hrms.business.abstracts.ImageService;
import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.business.abstracts.LinkService;
import kodlamaio.hrms.business.abstracts.SkillService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.adapters.MernisDemoService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.dtos.CvDto;

@Service
public class CvManager implements CvService{

	
	private ExperienceService experienceService;
	private EducationService educationService;
	private SkillService skillService;
	private LinkService linkService;
	private LanguageService languageService;
	private ImageService imageService;
	private CandidateService candidateService;
	
	@Autowired
	public CvManager(ExperienceService experienceService, EducationService educationService, SkillService skillService,
			LinkService linkService, LanguageService languageService, ImageService imageService,
			CandidateService candidateService) {
		super();
		this.experienceService = experienceService;
		this.educationService = educationService;
		this.skillService = skillService;
		this.linkService = linkService;
		this.languageService = languageService;
		this.imageService = imageService;
		this.candidateService = candidateService;
	}

	

	@Override
	public Result add(CvDto cvDto, int candidateId) {
		Candidate candidate = candidateService.getById(candidateId).getData();
		cvDto.setCandidate(candidate);
		
		cvDto.getExperiences().forEach(experience -> experience.setCandidate(candidate));
		experienceService.addAll(cvDto.getExperiences());
		
		cvDto.getLanguages().forEach(language -> language.setCandidate(candidate));
		languageService.addAll(cvDto.getLanguages());
		
		cvDto.getSkills().forEach(skill -> skill.setCandidate(candidate));
		skillService.addAll(cvDto.getSkills());
		
		cvDto.getLinks().forEach(link -> link.setCandidate(candidate));
		linkService.addAll(cvDto.getLinks());
		
		cvDto.getEducations().forEach(education -> education.setCandidate(candidate));
		educationService.addAll(cvDto.getEducations());
		
		
		
		
	return null;	
	}

}
