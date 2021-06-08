package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Education;
import kodlamaio.hrms.entities.concretes.Language;

public interface EducationService {
	public DataResult<List<Education>> getAll();
	public Result add(Education education);
	DataResult<List<Education>> getByCandidateId(int id);
	Result addAll(List<Education> educations);
	

}
