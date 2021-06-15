package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cv_work_experiences")
public class Experience {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="experience_id")
	private int id;
	
	@Column(name="workplace_name")
	private String workPlaceName;
	
	@Column(name ="starting_date")
	private LocalDate startingDate;
	

	@Column(name="ending_date")
	private LocalDate endingDate;
	

	@ManyToOne
	@JsonIgnore
    @JoinColumn(name = "cv_id")
    private Cv cv;
	
	
	@ManyToOne
	@JoinColumn(name="position_id")
	private JobPosition jobPosition;

	
}
