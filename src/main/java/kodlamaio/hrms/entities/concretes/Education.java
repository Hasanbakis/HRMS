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
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cv_educations")
@Entity
public class Education {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="education_id")
	private int id;
	
	@Column(name="education_name")
	private String educationName;
	
	@Column(name="departman_name")
	private String departmanName;
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@Column(name="graduation_date")
	private LocalDate graduationDate;
	
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name = "cv_id")
    private Cv cv;
	
	

}
