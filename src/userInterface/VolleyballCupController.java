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
    private ScrollPane fieldToPaint;

    @FXML
    private Label timeSearchSpectator;

    @FXML
    private Rectangle avatarImage;

    
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
    	
    	if(idSpectator.getText() != null) {
    		Person personFound = cup.searchPersonById(idSpectator.getText());
    		
    		if(personFound!=null) {
    			informationLabel.setText(personFound.toString());
    			avatarImage.setFill(new ImagePattern(new Image(personFound.getPhoto())));
    			
    		}
    	}
    }


    @FXML
    void searchParticipant(ActionEvent event) {

    }

    @FXML
    void paintParticipantStructure(ActionEvent event) {

    }

    @FXML
    void paintSpectatorStructure(ActionEvent event) {

    }

}
