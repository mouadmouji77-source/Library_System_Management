package model;

public abstract class Utilisateur {
	private String role;
	private int id;
	
	public Utilisateur(String role,int i) {
		this.role = role;
		this.id = i;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRole(String r) {
		role = r;
	}
	
	public String getRole() {
		return role;
	}
}
