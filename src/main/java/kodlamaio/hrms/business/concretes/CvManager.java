package kodlamaio.hrms.business.concretes;

import java.util.List;

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
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.CandidateDoa;
import kodlamaio.hrms.dataAccess.CvDao;
import kodlamaio.hrms.dataAccess.ImageDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Cv;
import kodlamaio.hrms.entities.concretes.Education;
import kodlamaio.hrms.entities.concretes.Experience;
import kodlamaio.hrms.entities.concretes.Image;
import kodlamaio.hrms.entities.concretes.Link;
import kodlamaio.hrms.entities.dtos.CvDto;
import net.bytebuddy.asm.Advice.This;

@Service
public class CvManager implements CvService{
	     private CvDao cvDao;
	     private CandidateDoa candidateDao;
	     private ImageDao imageDao;
	     
	     
	@Autowired
	public CvManager(CvDao cvDao, CandidateDoa candidateDao, ImageDao imageDao) {
			super();
			this.cvDao = cvDao;
			this.candidateDao = candidateDao;
			this.imageDao = imageDao;
		}



	@Override
	public DataResult<List<Cv>> getAll() {
		return new SuccessDataResult<List<Cv>>(this.cvDao.findAll(),"Data listed");
	}

	@Override
	public DataResult<Cv> getByCvId(int cvId) {
		return null;
	}

	@Override
	public DataResult<Cv> getByCandidateId(int candidateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result add(Cv cv) {
		this.cvDao.save(cv);
		Image image=new Image();
        image.setImageUrl("https://w7.pngwing.com/pngs/340/946/png-transparent-avatar-user-computer-icons-software-developer-avatar-child-face-heroes.png");
        this.imageDao.save(image);
		return new SuccessResult("Cv eklendi");
	}

	

	

	

	

	
	

}
