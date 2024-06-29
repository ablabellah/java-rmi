package classapp;

import java.io.Serializable;

public class AnnuaireEntree implements Serializable {

	private static final long serialVersionUID = 2290645081309697371L;
	
	private String name;
	private String lastname;
	private String phone;

	public AnnuaireEntree(String name, String lastname, String phone) {
		this.name = name;
		this.lastname = lastname;
		this.phone = phone;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AnnuaireEntree [name=");
		builder.append(name);
		builder.append(", lastname=");
		builder.append(lastname);
		builder.append(", phone=");
		builder.append(phone);
		builder.append("]");
		return builder.toString();
	}
}
