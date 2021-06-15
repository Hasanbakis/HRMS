	package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Cv;
import kodlamaio.hrms.entities.dtos.CvDto;

public interface CvService {

	 public DataResult<List<Cv>> getAll();
	public Result add(Cv cv);
	 public DataResult<Cv> getByCvId(int cvId);
	 public DataResult<Cv> getByCandidateId(int candidateId);

}
    