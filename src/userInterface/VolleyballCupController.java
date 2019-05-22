package userInterface;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Person;
import model.VolleyballCup;

public class VolleyballCupController {
	
	private int posY;
	
	private ImageNode root;
	
	@FXML
	private ComboBox<String> countryOptions;
	
	private VolleyballCup cup;
	
	@FXML
    private TextField fieldFile;
	
	@FXML
	private Label labelFile;
	 
	@FXML
    private TextField idParticipant;

	@FXML
	private TextField idSpectator;
	
    @FXML
    private Label timeSearchParticipant;

    @FXML
    private Label informationLabel;

    @FXML
    private Label labelParticipant;
    
    @FXML
    private Label labelSpectator;
    
    @FXML
    private Label timeSearchSpectator;

    @FXML
    private Rectangle avatarImage;
    
    @FXML
    private Pane paintPane;

    
    @FXML
    public void initialize() {
    	cup = new VolleyballCup("IV Copa Panamericana de Voleibol Masculino Sub-21", "mayo de 2019", "lima");
    	paintPane.setPrefHeight(50100);
    	paintPane.setPrefWidth(50100);
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
	            List<String> countries = cup.getParticipantCountries();
	            ObservableList<String> items = FXCollections.observableArrayList();
	            items.addAll(countries);
	            countryOptions.setItems(items);
	            countryOptions.setVisible(true);
				labelFile.setText("the information has been loaded correctly");
			} catch (IOException e) {
				labelFile.setText("invalid route, try again");
			}
    	}else {
    		labelFile.setText("invalid route, try again");
    	}

    }

   
    @FXML
    void searchSpectator(ActionEvent event) {
    	long time = System.currentTimeMillis();
    	
    	if(!idSpectator.getText().isEmpty()) {
    		Person personFound = cup.searchPersonById(idSpectator.getText());
    		
    		if(personFound!=null) {
    			informationLabel.setText(personFound.toString());
    			avatarImage.setFill(new ImagePattern(new Image(personFound.getPhoto())));
    			
    		}else {
    			labelSpectator.setText("the person with this id was not found");
    		}
    	}else {
    		labelSpectator.setText("enter an id please");
    	}
    	
    	time = (System.currentTimeMillis()-time)/1000;
    	
    	timeSearchSpectator.setText(""+time + " s");
    }


    @FXML
    void searchParticipant(ActionEvent event) {
    	long time = System.currentTimeMillis();
    	
    	if(!idParticipant.getText().isEmpty()) {
    		Person personFound = cup.searchParticipantById(idParticipant.getText());
    		
    		if(personFound!=null) {
    			informationLabel.setText(personFound.toString());
    			avatarImage.setFill(new ImagePattern(new Image(personFound.getPhoto())));
    			
    		}else {
    			labelParticipant.setText("the person with this id was not found");
    		}
    	}else {
    		labelParticipant.setText("enter an id please");
    	}
    	
    	time = (System.currentTimeMillis()-time)/1000;
    	
    	timeSearchParticipant.setText(""+time + " s");
    	
    }

    @FXML
    void paintParticipantStructure(ActionEvent event) {
        paintPane.getChildren().clear();
        if(countryOptions.getValue() != null) {
            String country = countryOptions.getValue();
             List<Person> participants = cup.getParticipants();
         
             int x = 14;
             int counter = 1;
             for (int i = 0; i < participants.size(); i++) {
                 if(participants.get(i).getCountry().equals(country)) {
                     if(i != 0 && !paintPane.getChildren().isEmpty()) {
                         Rectangle lines = new Rectangle(50, 50);
                         lines.setX(x);
                         lines.setY(139);
                         lines.setFill(new ImagePattern(new Image("userInterfaceImages/lines.png")));
                         paintPane.getChildren().add(lines);
                         Rectangle avatar = new Rectangle(50, 50);
                         avatar.setX(x+50);
                         avatar.setY(139);
                         avatar.setFill(new ImagePattern(new Image(participants.get(i).getPhoto())));
                         paintPane.getChildren().add(avatar);
                         x+=100;
                         counter += 2;
                     }else {
                         Rectangle avatar = new Rectangle(50, 50);
                         avatar.setX(x);
                         avatar.setY(139);
                         avatar.setFill(new ImagePattern(new Image(participants.get(i).getPhoto())));
                         paintPane.getChildren().add(avatar);
                         x+=50;
                         counter++;
                     }
                 }
             }
           //  paintPane.setPrefWidth(counter*50);
        }else {
            countryOptions.requestFocus();
        }
    }

   public void addPersonToTree(ImageNode node) {
        
        if(root == null) {
            root = node;
        }else {
            ImageNode current = root;
            boolean exit = false;
            
            while(!exit) {
                
                if(current.compareTo(node) >0) {
                    
                    if(current.getLeft() == null) {
                        current.setLeft(node);
                        exit = true;
                    }else {
                        current = current.getLeft();
                    }
                }else {
                    if(current.getRight() == null) {
                        current.setRight(node);
                        exit = true;
                    }else {
                        current = current.getRight();
                    }
                }
                
            }
        }
    }

   public void drawLines(ImageNode root) {
       if(root.getLeft() != null) {
           ImageNode other = root.getLeft();
           Line newLine = new Line(root.getRectangle().getX() + 50, root.getRectangle().getY() + 25, other.getRectangle().getX(), other.getRectangle().getY() + 25);
           paintPane.getChildren().add(newLine);
           drawLines(root.getLeft());
       }
       if(root.getRight() != null) {
           ImageNode other = root.getRight();
           Line newLine = new Line(root.getRectangle().getX() + 50, root.getRectangle().getY() + 25, other.getRectangle().getX(), other.getRectangle().getY() + 25);
           paintPane.getChildren().add(newLine);
           drawLines(root.getRight());
       }
   }

   public void print2DUtil(ImageNode root, int space)
   {
       // Base case
       if (root == null)
           return;

       // Increase distance between levels
       space += 10;

       // Process right child first
       print2DUtil(root.getRight(), space);

       // Print current node after space
       // count
       root.getRectangle().setY(posY+50);
       posY += 50;
       for (int i = 10; i < space; i++)  {
           root.getRectangle().setX(root.getRectangle().getX()+10);
       }
       paintPane.getChildren().add(root.getRectangle());
       posY += 50;

       // Process left child
       print2DUtil(root.getLeft(), space);
   }

   // Wrapper over print2DUtil()
   public void print2D(ImageNode root)
   {
       // Pass initial space count as 0
       posY = 0;
       print2DUtil(root, 0);
   }
   
   @FXML
   void paintSpectatorStructure(ActionEvent event) {
       paintPane.getChildren().clear();
       root = null;
       if(countryOptions.getValue() != null) {
           String country = countryOptions.getValue();
           List<Person> participants = cup.preOrder(country);
           for (int i = 0; i < participants.size(); i++) {
               Rectangle avatar = new Rectangle(50, 50);
               avatar.setFill(new ImagePattern(new Image(participants.get(i).getPhoto())));
                addPersonToTree(new ImageNode(participants.get(i).getId(), avatar));
            }
           print2D(root);
           drawLines(root);
       }else {
           countryOptions.requestFocus();
       }
       
   }

}
