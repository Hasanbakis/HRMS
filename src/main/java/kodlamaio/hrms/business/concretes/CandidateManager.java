package kodlamaio.hrms.business.concretes;


import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.EducationService;
import kodlamaio.hrms.business.abstracts.ExperienceService;
import kodlamaio.hrms.business.abstracts.ImageService;
import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.business.abstracts.LinkService;
import kodlamaio.hrms.business.abstracts.SkillService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.adapters.MernisDemoService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.CandidateDoa;
import kodlamaio.hrms.dataAccess.UserDao;
import kodlamaio.hrms.entities.abstracts.User;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.dtos.CvDto;


@Service
public class CandidateManager implements CandidateService{

	private CandidateDoa candidateDao;
	private UserDao userDao;
	private VerificationCodeService verificationCodeService;
	private MernisDemoService mernisDemoService;
	private ExperienceService experienceService;
	private EducationService educationService;
	private SkillService skillService;
	private LinkService linkService;
	private LanguageService languageService;
	private ImageService imageService;
	
	@Autowired
	public CandidateManager(CandidateDoa candidateDao, UserDao userDao, VerificationCodeService verificationCodeService,
			MernisDemoService mernisDemoService, ExperienceService experienceService, EducationService educationService,
			SkillService skillService, LinkService linkService, LanguageService languageService,ImageService imageService) {
		super();
		this.candidateDao = candidateDao;
		this.userDao = userDao;
		this.verificationCodeService = verificationCodeService;
		this.mernisDemoService = mernisDemoService;
		this.experienceService = experienceService;
		this.educationService = educationService;
		this.skillService = skillService;
		this.linkService = linkService;
		this.languageService = languageService;
		this.imageService=imageService;
	}



	
	

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(),"Candidates successfully listed");
	}

	@Override
	public Result add(Candidate candidate) {
		if(candidate.getFirstName() ==null) {
			return new ErrorResult("Username is required");
		}
		else if(candidate.getLastName()==null) {
			return new ErrorResult("Last name is required");
		}
		else if(candidate.getBirthDate()==null) {
			return new ErrorResult("Date of birth is required");
		}
		else if(candidate.getEmail()==null) {
			return new ErrorResult("Email is required");
		}
		else if (candidate.getPassword().length() <= 7) {
			return new ErrorResult("Password is required");
		}
		else if(!mernisDemoService.isValidNationolityIdentity(candidate.getIdentificationNumber())) {
			return new ErrorResult("Identity number is not correct");
		}
		else if(isEmailValid(candidate.getEmail())) {
			candidate.setMailVerify(false);
			User savedUser = this.userDao.save(candidate);
			
			this.verificationCodeService.createActivationCode(savedUser);//$$$$$
			this.candidateDao.save(candidate);
			return new SuccessResult("Verification code sent to "+candidate.getEmail());
			
			
			
			
		}else {
			return new ErrorResult("User information is incorrect");
		}
	}
	
	@Override
	public DataResult<Candidate> getById(int id) {
		return new SuccessDataResult<Candidate>(this.candidateDao.findById(id).get());
	}
	
	
	
	private final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";
	private boolean isEmailValid(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
		return pattern.matcher(email).find();
	}

	@Override
	public DataResult<CvDto> getCvById(int id) {
		CvDto cvDto = new CvDto();
		cvDto.setCandidate(this.getById(id).getData());
		cvDto.setExperiences(this.experienceService.getByCandidateIdOrderByStartingDate(id).getData());
		cvDto.setLanguages(this.languageService.getByCandidateId(id).getData());
		cvDto.setEducations(this.educationService.getByCandidateId(id).getData());
		cvDto.setLinks(this.linkService.getByCandidateId(id).getData());
		cvDto.setImages(this.imageService.getByCandidateId(id).getData());
		return new SuccessDataResult<>(cvDto);
	}

}
