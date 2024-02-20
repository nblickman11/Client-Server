import java.io.IOException;
import java.util.TreeSet;

public class CircularShifter {

	TreeSet<String> treeSetSorted = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);

	public String positionWords(String word, String shiftedString, int i, int wordsInCurrentLine) {
		if (i == wordsInCurrentLine - 2) {
			shiftedString = shiftedString.concat(word);
		} else if (i != wordsInCurrentLine - 1) {
			shiftedString = shiftedString.concat(word + " ");
		}
		return shiftedString;
	}

	public String shiftCurrentLineOnce(String currentLine) {
		String[] wordsInCurrentLine = currentLine.split("\\s");
		String shiftedString = ("" + wordsInCurrentLine[wordsInCurrentLine.length - 1] + " ");
		int i = 0;
		for (String word : wordsInCurrentLine) {
			shiftedString = positionWords(word, shiftedString, i, wordsInCurrentLine.length);
			i++;
		}
		return shiftedString;
	}

	public void passNewLineToBeShifted(String currentLine, Alphabetizer alphabetizer) throws IOException {
		int totalShifts = currentLine.split("\\s").length;
		for (int counter = 0; counter < totalShifts; counter++) {
			String shiftedString = shiftCurrentLineOnce(currentLine);
			currentLine = shiftedString;
			alphabetizer.addToAlphabetizedSet(shiftedString);
		}
	}

}
