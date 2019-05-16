package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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

	
	
	
	public void loadInformation(String path) throws IOException {
		File fileCsv = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(fileCsv));
		
		br.readLine();  // eat first line
		
		String line = "";
		List<Person> selectedPersons = new ArrayList<Person>();
		
		while((line = br.readLine()) != null) {
			
			StringTokenizer st = new StringTokenizer(line, ",");
			
			addPersonToTree(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken());
			
			st = new StringTokenizer(line, ",");
			
			Person person = new Person(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken());
			selectedPersons.add(person);
		}
		br.close();
		
		int size = selectedPersons.size();
		boolean[] visited = new boolean[size];
		for (int i = 0; i < size/2; i++) {
			int random = (int) (0 + Math.random()*size);
			if(!visited[random]) {
				addParticipantToList(selectedPersons.get(random));
				visited[random] = true;
			}else {
				i--;
			}
			
			if(i == 200) {
				System.out.println(selectedPersons.get(random).getId());
			}
		}
	}
	
	
	public void addParticipantToList(Person participant) {
		
		if(firstParticipant == null) {
			firstParticipant = participant;
		}else {
			Person current = firstParticipant;
			
			while(current.getNext() != null) {
				current = current.getNext();
			}
			
			current.setNext(participant);
			participant.setPrev(current);
		}
		
	}
	
	public void addPersonToTree(String id, String firstName, String lastName, String email, String gender, String country, String photo, String birthday) {
		Person newPerson = new Person(id, firstName, lastName, email, gender, country, photo, birthday);
		
		if(rootSpectator == null) {
			rootSpectator = newPerson;
		}else {
			Person current = rootSpectator;
			boolean exit = false;
			
			while(!exit) {
				
				if(current.compareTo(newPerson) >0) {
					
					if(current.getLeft() == null) {
						current.setLeft(newPerson);
						exit = true;
					}else {
						current = current.getLeft();
					}
				}else {
					if(current.getRight() == null) {
						current.setRight(newPerson);
						exit = true;
					}else {
						current = current.getRight();
					}
				}
				
			}
		}
	}
	
	
	public Person searchPersonById(String id) {
		
		Person current = rootSpectator;
		boolean exit = false;
		
		
		while(!exit && current != null) {
			
			if(current.getId().equals(id)) {
				exit = true;
			}else {
				if(current.getId().compareTo(id) >0) {
					current = current.getLeft();
				}
				else {
					current = current.getRight();
				}
			}
			
		}
		
		return current;
		
	}
	
	public Person searchParticipantById(String id) {
		
		Person current = firstParticipant;
		boolean found = false;
		
		while(!found && current != null) {
			
			if(current.getId().equals(id)) {
				found = true;
			}else {
				current = current.getNext();
			}
		}
		
		return current;
		
	}
	
	
	public List<Person> getParticipants(){
		List<Person> participants = new ArrayList<Person>();

		Person current = firstParticipant;
		
		while(current != null) {
			participants.add(current);
			current = current.getNext();
		}
		
		return participants;
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
