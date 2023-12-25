import java.io.Serializable;
import java.util.TreeMap;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Dictionary implements Serializable {

	private TreeMap<String, String> dictionary = new TreeMap<String, String>(); 
	private Alert info;

	//Import from an external dictionary (when loaded from a file).
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary.getDictionary();
	}

	public TreeMap<String, String> getDictionary() {
		return dictionary;
	}

	// Add a term the dictionary. Addition is made only if the term does not already exist in the dictionary, 
	//only if it is valid.
	public void add(String term, String interpretation) throws Exception {
		if (dictionary.containsKey(term)) {
			throw new Exception("The term \"" + term + "\" already exists in the dictionary");
		} else if (!validateTerm(term)) {
			throw new Exception("The term \"" + term + "\" is invalid because it includes digits.");
		}
		dictionary.put(term, interpretation);
	}

	//Term update from the dictionary. An update is made only if the term already exists in the dictionary, 
	//and only if it is valid.
	public void update(String term, String interpretation) throws Exception {
		if (!dictionary.containsKey(term)) {
			throw new Exception("The term \"" + term + "\" already exists in the dictionary");
		} else if (!validateTerm(term)) {
			throw new Exception("The term \"" + term + "\" is invalid, because it includes digits.");
		}
		dictionary.put(term, interpretation);
	}

	//Delete a term from the dictionary. Deletion is performed only if the term already exists in the dictionary.
	public void delete(String term) throws Exception {
		if (!dictionary.containsKey(term)) {
			throw new Exception("The term \"" + term + "\" already exists in the dictionary");
		}
		dictionary.remove(term);
	}

	//A term search in a dictionary. A result is obtained only if the term already exists in the dictionary.
	public TreeMap<String, String> search(String term) {
		info = new Alert(AlertType.INFORMATION);
		if (!dictionary.containsKey(term)) {
			info.setContentText("Sorry, no items matched your search.");
			info.show();
			return new TreeMap<String,String>(); //Empty tree is returned
		}
		return new TreeMap<String, String>() {{put(term, dictionary.get(term));}}; //The result tree is returned
	} 

	//Check if the term is valid, that is, if it contains only letters without numbers.
	private boolean validateTerm(String term) {
		if (term.matches("^[A-Za-z]*$")) {
			return true;
		}
		return false;
	}

}
