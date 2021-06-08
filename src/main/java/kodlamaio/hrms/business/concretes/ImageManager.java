package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.ImageService;
import kodlamaio.hrms.core.utilities.imageUpload.ImageUploadService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.ImageDao;
import kodlamaio.hrms.entities.concretes.Image;
import net.bytebuddy.asm.Advice.This;

@Service
public class ImageManager implements ImageService{

	private ImageDao imageDao;
    ImageUploadService imageUploadService;
	
	@Autowired
	public ImageManager(ImageDao imageDao, ImageUploadService imageUploadService) {
		
		this.imageDao = imageDao;
		this.imageUploadService = imageUploadService;
	}

	@Override
	public Result add(Image image, MultipartFile imageFile) {
		Map<String, String> uploadImage= this.imageUploadService.uploadImageFile(imageFile).getData();
		image.setName(uploadImage.get("name"));
		this.imageDao.save(image);
		return new SuccessResult("The picture successfully added");
	}


	@Override
	public DataResult<List<Image>> getByCandidateId(int id) {
		return new SuccessDataResult<List<Image>>(this.imageDao.getByCandidateId(id),"Foto listelendi");
	}

	



	
	

}
