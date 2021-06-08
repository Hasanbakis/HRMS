package kodlamaio.hrms.entities.concretes;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kodlamaio.hrms.entities.abstracts.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false) 
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="candidates")
public class Candidate extends User{
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="identification_number")
	private String identificationNumber;
	
	@Column(name="birth_date")
	private LocalDate birthDate;
	
	@OneToMany(mappedBy = "candidate")
	private List<Image> images;
	
	@OneToMany(mappedBy = "candidate")
	private List<Language> languages;
	
	@OneToMany(mappedBy = "candidate")
	private List<Education> educations;
	
	@OneToMany(mappedBy = "candidate")
	private List<Skill> skills;
	
	@OneToMany(mappedBy = "candidate")
	private List<Link> links;
	

	@OneToMany(mappedBy = "candidate")
	private List<Experience> experiences;

}
