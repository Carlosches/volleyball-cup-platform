package model;

public class Person implements Comparable<Person>{
	public static final String FEMALE = "Female";
	public static final String MALE = "Male";
	
	private String id;
	private String first_name;
	private String last_name;
	private String email;
	private String gender;
	private String country;
	private String photo;
	private String birthday;
	
	private Person right;
	private Person left;
	private Person prev;
	private Person next;
	
	/**
	 * @param id
	 * @param first_name
	 * @param last_name
	 * @param email
	 * @param gender
	 * @param country
	 * @param photo
	 * @param birthday
	 */
	public Person(String id, String first_name, String last_name, String email, String gender, String country,
			String photo, String birthday) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.gender = gender;
		this.country = country;
		this.photo = photo;
		this.birthday = birthday;
	}

	@Override
	public int compareTo(Person otherPerson) {
		return id.compareTo(otherPerson.id);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", gender=" + gender + ", country=" + country + ", photo=" + photo + ", birthday=" + birthday + "]";
	}
	
	
	
	
}
