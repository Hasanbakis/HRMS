package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Education;
import kodlamaio.hrms.entities.concretes.Language;
import kodlamaio.hrms.entities.concretes.Link;


public interface LinkService {
	public DataResult<List<Link>> getAll();
	public Result add(Link link);
	DataResult<List<Link>> getByCandidateId(int id);
	Result addAll(List<Link> links);

}
