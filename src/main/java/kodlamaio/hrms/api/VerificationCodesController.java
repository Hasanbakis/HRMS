package kodlamaio.hrms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.VerificationCode;

@RestController
@RequestMapping("/api/verificationCodes")
public class VerificationCodesController {
	
	private VerificationCodeService verificationCodeService;
	
	@Autowired
	public VerificationCodesController(VerificationCodeService verificationCodeService) {
		super();
		this.verificationCodeService = verificationCodeService;
	}

	
	@GetMapping("/getall")
	public DataResult<List<VerificationCode>> findAllByCode(){
		return verificationCodeService.findAllByCode();
	}
	
	@GetMapping("/active/{code}") //@PathVariable : degeri path'den almak icin 
	public Result activeUser(@PathVariable String code) {
		return verificationCodeService.activateUser(code);
	}


}
