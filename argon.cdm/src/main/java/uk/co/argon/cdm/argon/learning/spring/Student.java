package uk.co.argon.cdm.argon.learning.spring;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "STUDENT")
public class Student {
	
	@Id
	@SequenceGenerator(name="student_seq", sequenceName = "system.student_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "student_seq")
	private Long id;
	private String name;
	private String lastName;
	private String email;
	private LocalDate dob;
	@Transient
	private Integer age;
	
	public Student() {
	}
	
	public Student(String name, String lastName, String email, LocalDate dob) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.dob = dob;
	}

	public Student(Long id, String name, String lastName, String email, LocalDate dob) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.dob = dob;
	}



	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the dob
	 */
	public LocalDate getDob() {
		return dob;
	}
	/**
	 * @param dob the dob to set
	 */
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	/**
	 * @return the age
	 */
	public Integer getAge() {
		return Period.between(dob, LocalDate.now()).getYears();
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", lastName=" + lastName + ", email=" + email + ", dob=" + dob
				+ ", age=" + age + "]";
	}

}
