package model;

public class VolleyballCup {
	
	private String name;
	private String date;
	private String city;
	
	private Person firstParticipant;
	private Person rootSpectator;
	
	public VolleyballCup(String name, String date, String city) {
		this.name = name;
		this.date = date;
		this.city = city;
	}

	
	
	
	public void loadInformation(String path) {
		
	}
	
	
	public void addParticipantToList(Person participant) {
		
	}
	
	public void addPersonToTree(String id, String firstName, String lastName, String email, String gender, String country, String photo, String birthday) {
		
	}
	
	
			
	
	
	
	/**
	 * @return the firstParticipant
	 */
	public Person getFirstParticipant() {
		return firstParticipant;
	}

	/**
	 * @param firstParticipant the firstParticipant to set
	 */
	public void setFirstParticipant(Person firstParticipant) {
		this.firstParticipant = firstParticipant;
	}

	/**
	 * @return the rootSpectator
	 */
	public Person getRootSpectator() {
		return rootSpectator;
	}

	/**
	 * @param rootSpectator the rootSpectator to set
	 */
	public void setRootSpectator(Person rootSpectator) {
		this.rootSpectator = rootSpectator;
	}
	
	
	
	
	
	
}
