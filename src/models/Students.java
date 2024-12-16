package models;

public class Students {
	static int lastId;
	public String firstname;
	public String lastname;
	public String email;
	public int id;
	public Students(String firstname, String lastname, String email, int id) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.id = id;
		
	}
	public Students(String firstname, String lastname, String email) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.id = ++lastId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
