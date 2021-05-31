package kodlamaio.hrms.business.concretes;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.EmployerDao;
import kodlamaio.hrms.dataAccess.UserDao;
import kodlamaio.hrms.entities.abstracts.User;
import kodlamaio.hrms.entities.concretes.Employer;


@Service
public class EmployerManager implements EmployerService{

	private EmployerDao employerDao;
	private UserDao userDao;
	private VerificationCodeService verificationCodeService;
	
	
	@Autowired
	public EmployerManager(EmployerDao employerDao, UserDao userDao, VerificationCodeService verificationCodeService) {
		super();
		this.employerDao = employerDao;
		this.userDao = userDao;
		this.verificationCodeService = verificationCodeService;
	}


	

	@Override
	public Result add(Employer employer) {
		if(!checkIfMissingInformationEntry(employer)) {
			return new ErrorResult("Missing information input.");
		}
		if(!checkIfEmailExists(employer.getEmail())) {
			return new ErrorResult("User email already exists");
		}
		if(!checkIfTheEmailFormatIsCorrect(employer.getEmail())) {
			return new ErrorResult("Make sure it is in e-mail format and try again.");
		}
		if(!checkIfEqualEmailAndDomain(employer.getEmail(), employer.getWebAddress())) {
			return new ErrorResult("Make sure it's an email with the same domain as the website.");
		}
		employer.setMailVerify(false);
		User savedUser = this.userDao.save(employer);
		
		this.employerDao.save(employer);
		this.verificationCodeService.createActivationCode(savedUser);//$$$$$
		return new SuccessResult("Verification code sent to "+employer.getEmail());
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll());
	}

	@Override
	public DataResult<Employer> getByEmail(String email) {
		return new SuccessDataResult<Employer>(this.employerDao.findByEmail(email));
	
	}
	private boolean checkIfMissingInformationEntry(Employer employer){
		if(employer.getEmail()==null && employer.getPassword()==null && employer.getCompanyName()==null
				&&employer.getWebAddress()==null) {
			
			return false;
		}
		return true;
			
	}
		
	
	
	private boolean checkIfEmailExists(String email) {
		if(this.employerDao.findByEmail(email) !=null) {
			return false;
		}
		return true;
	}
	
	private boolean checkIfTheEmailFormatIsCorrect(String email) {
		String regex ="^(.+)@(.+)$";
		Pattern pattern =Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	private boolean checkIfEqualEmailAndDomain(String email,String website) {
		String[] webAddressKeywords= {"www","http://www","https://www" };
		
		String[] emailDomain = email.split("@",2);
		
		String domain ="";
		
		String[] websiteDomain = website.split("\\.",2);
		
		if(Arrays.asList(webAddressKeywords).contains(websiteDomain[0])) {
			domain=websiteDomain[1];
			
		}else {
			domain=website;
		}
		
		
		if(!emailDomain[1].equals(domain)) {
			System.out.println("Domain error");
			return false;
			
		}
		
		return  true;
		
	}

}
