package userInterface;

import java.io.File;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
    void exploreFile(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("PDF files", "*.pdf"));
    	File file = fc.showOpenDialog(null);
    	
    	if(file != null)
    		fieldFile.setText(file.getAbsolutePath());
    	
    }

    @FXML
    void loadFile(ActionEvent event) {
    	
    	if(fieldFile.getText() != null) {
    		cup.loadInformation(fieldFile.getText());
    	}	

    }



   
    @FXML
    void searchSpectator(ActionEvent event) {

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
