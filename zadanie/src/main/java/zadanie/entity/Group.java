package zadanie.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "group")
public class Group {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	@ManyToOne
	private User users;
	
	
	public Group() {
		super();
	}


	public Group(String name, User users) {
		super();
		this.name = name;
		this.users = users;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public User getUsers() {
		return users;
	}


	public void setUsers(User users) {
		this.users = users;
	}

}
