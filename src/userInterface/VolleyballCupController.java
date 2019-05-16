package userInterface;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Person;
import model.VolleyballCup;

public class VolleyballCupController {
	
	private VolleyballCup cup;
	
	@FXML
    private TextField fieldFile;
	
	@FXML
    private TextField idParticipant;

	@FXML
	private TextField idSpectator;
	
    @FXML
    private Label timeSearchParticipant;

    @FXML
    private Label informationLabel;


    @FXML
    private Label timeSearchSpectator;

    @FXML
    private Rectangle avatarImage;
    
    @FXML
    private Pane paintPane;

    
    @FXML
    public void initialize() {
    	cup = new VolleyballCup("IV Copa Panamericana de Voleibol Masculino Sub-21", "mayo de 2019", "lima");
    }
    @FXML
    void exploreFile(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("CSV files", "*.csv"));
    	File file = fc.showOpenDialog(null);
    	
    	if(file != null)
    		fieldFile.setText(file.getAbsolutePath());
    	
    }

    @FXML
    void loadFile(ActionEvent event) {
    	
    	if(fieldFile.getText() != null) {
    		try {
				cup.loadInformation(fieldFile.getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}	

    }



   
    @FXML
    void searchSpectator(ActionEvent event) {
    	long time = System.currentTimeMillis();
    	
    	if(idSpectator.getText() != null) {
    		Person personFound = cup.searchPersonById(idSpectator.getText());
    		
    		if(personFound!=null) {
    			informationLabel.setText(personFound.toString());
    			avatarImage.setFill(new ImagePattern(new Image(personFound.getPhoto())));
    			
    		}
    	}
    	
    	time = (System.currentTimeMillis()-time)/1000;
    	
    	timeSearchSpectator.setText(""+time + " s");
    }


    @FXML
    void searchParticipant(ActionEvent event) {
    	long time = System.currentTimeMillis();
    	
    	if(idParticipant.getText() != null) {
    		Person personFound = cup.searchParticipantById(idParticipant.getText());
    		
    		if(personFound!=null) {
    			informationLabel.setText(personFound.toString());
    			avatarImage.setFill(new ImagePattern(new Image(personFound.getPhoto())));
    			
    		}
    	}
    	time = (System.currentTimeMillis()-time)/1000;
    	
    	timeSearchParticipant.setText(""+time + " s");
    	
    }

    @FXML
    void paintParticipantStructure(ActionEvent event) {
    	
    	List<Person> participants = cup.getParticipants();
	
    	int x = 14;
    	for (int i = 0; i < participants.size(); i++) {
    		
    		Rectangle avatar = new Rectangle(50, 50);
    		avatar.setX(x); 
    		avatar.setY(139);
    		avatar.setFill(new ImagePattern(new Image("userInterfaceImages/lines.png")));
 
    		if(i!= participants.size()-1) {
	    		Rectangle lines = new Rectangle(50, 50);
	    		lines.setX(x+50);
	    		lines.setY(139);
	    		lines.setFill(new ImagePattern(new Image("userInterfaceImages/lines.png")));
	    		paintPane.getChildren().add(lines);
    		}
    		x+=100;
    		
    		paintPane.getChildren().add(avatar);
		}
    	
    }

    @FXML
    void paintSpectatorStructure(ActionEvent event) {

    }

}
