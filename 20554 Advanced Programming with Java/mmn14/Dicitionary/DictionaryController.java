import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert.AlertType;

public class DictionaryController {

	@FXML private TableView<Map.Entry<String, String>> table;
	@FXML private TableColumn<Map.Entry<String, String>, String> termColumn;
	@FXML private TableColumn<Map.Entry<String, String>, String>  interpretationColumn;
	@FXML private TextField addOrUpdateOrDeleteTerm;
	@FXML private TextField addOrUpdateInterpretation;
	@FXML private TextField searchTerm;
	@FXML private TextField fileNameSaveOrLoad;
	private Alert error;
	private Alert info;
	private Dictionary dictionary;

	public DictionaryController() {
		dictionary = new Dictionary();
		error = new Alert(AlertType.ERROR);
		info = new Alert(AlertType.INFORMATION);
	}

	//Initialize the display when there are no values
	@FXML
	private void initialize() {
		table.setPlaceholder(new Label("There are no items to display"));
		termColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));
		interpretationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue()));
	}

	//Absorb a term and interpretation from the text fields and send to a function that adds values to the dictionary
	@FXML
	private void add() {
		//Check if the two text fields are filled
		if ((!addOrUpdateOrDeleteTerm.getText().isEmpty()) && (!addOrUpdateInterpretation.getText().isEmpty())) {
			try {
				dictionary.add(addOrUpdateOrDeleteTerm.getText(), addOrUpdateInterpretation.getText());
			} catch (Exception e) {
				error.setContentText(e.getMessage());
				error.show();
			}
			viewDictionary();
		};
		clearFields();
	}

	//Absorption of a term and interpretation from the text fields and sending to a function that updates values in a dictionary
	@FXML 
	private void update() {
		//Check if the two text fields are filled
		if ((!addOrUpdateOrDeleteTerm.getText().isEmpty()) && (!addOrUpdateInterpretation.getText().isEmpty())) {
			try {
				dictionary.update(addOrUpdateOrDeleteTerm.getText(), addOrUpdateInterpretation.getText());
			} catch (Exception e) {
				error.setContentText(e.getMessage());
				error.show();
			}
		}
		viewDictionary();
		clearFields(); 
	}

	//Inserts a term from a text field and sends a function that deletes entries in a dictionary
	@FXML
	private void delete() {
		String deleteTerm = addOrUpdateOrDeleteTerm.getText();
		try {
			dictionary.delete(deleteTerm);
			//Emphasis that the deletion is performed according to the term entered, 
			//and not according to the interpretation if entered.
			info.setContentText("The term \"" + deleteTerm + "\" and its meaning have been deleted, "
					+ "even if the interpretation did not match the entered interpretation");
			info.show();
		} catch (Exception e) {
			error.setContentText(e.getMessage());
			error.show();
		}
		viewDictionary();
		clearFields();
	}

	//Absorbs a term from the text field and sends it to a function that looks for values in a dictionary
	@FXML
	private void search() {
		addOrUpdateOrDeleteTerm.setText("");
		addOrUpdateInterpretation.setText("");
		fileNameSaveOrLoad.setText("");
		table.getItems().clear();
		table.getItems().addAll(dictionary.search(searchTerm.getText()).entrySet());
	}


	//Clearing the search results and displaying the entire dictionary
	@FXML
	private void clear() {
		clearFields();
		viewDictionary();
	}

	//View the entire dictionary
	@FXML 
	private void viewDictionary() {
		table.getItems().clear();
		table.getItems().addAll(dictionary.getDictionary().entrySet());
	}

	//Save the dictionary to a file
	@FXML
	private void saveToFile() {
		try {
			FileChooser fileChooser = new FileChooser();
			File selectedFile = fileChooser.showSaveDialog(null);
			FileOutputStream fo = new FileOutputStream (selectedFile);
			ObjectOutputStream out = new ObjectOutputStream(fo);
			out.writeObject(dictionary);
			out.close();
			fo.close();
			info.setContentText("The dictionary is saved in the file, in the location and name you selected");
			info.show();
		} catch (IOException e) {
			error.setContentText(e.getMessage());
			error.show();
		}
		clearFields();
	}

	//Upload a file containing a dictionary
	@FXML 
	private void loadFromFile() {
		try {
			FileChooser fileChooser = new FileChooser();
			File selectedFile = fileChooser.showOpenDialog(null);
			FileInputStream fi = new FileInputStream (selectedFile);
			ObjectInputStream ois = new ObjectInputStream(fi);
			dictionary.setDictionary((Dictionary)ois.readObject());
			ois.close();
			fi.close();
		} catch (IOException e) {
			error.setContentText(e.getMessage());
			error.show();
		} catch (ClassNotFoundException e) {
			error.setContentText(e.getMessage());
			error.show();
		}
		viewDictionary();
		clearFields();
	}

	//Clearing text fields
	private void clearFields() {
		addOrUpdateOrDeleteTerm.setText("");
		addOrUpdateInterpretation.setText("");
		searchTerm.setText("");
	}

}