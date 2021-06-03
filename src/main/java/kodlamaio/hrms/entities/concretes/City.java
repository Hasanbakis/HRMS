package kodlamaio.hrms.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cities")
@Entity
@PrimaryKeyJoinColumn(name = "city_id", referencedColumnName = "id")
//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "jobAdvertisement"})
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int cityId;
	
	@Column(name="name")
	private String cityName;
	
	//@OneToMany(mappedBy = "city")
	//private List<JobAdvertisement> jobAdvertisement;

}
