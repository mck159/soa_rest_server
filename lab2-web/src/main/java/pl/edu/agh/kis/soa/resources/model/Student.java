package pl.edu.agh.kis.soa.resources.model;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
public class Student {

	@Id
	@Column(name="id")
	@GenericGenerator(name="kaugen" , strategy="increment")
	@GeneratedValue(generator="kaugen")
	private int id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="album_number")
	private String albumNo;
	@ElementCollection
	private List<String> subjects;

	public Student() {
		super();
	}
	public Student(String firstName, String lastName, String albumNo,
			List<String> subjects) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.albumNo = albumNo;
		this.subjects = subjects;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAlbumNo() {
		return albumNo;
	}
	public void setAlbumNo(String albumNo) {
		this.albumNo = albumNo;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	public List<String> getSubjects() {
		return subjects;
	}


}
