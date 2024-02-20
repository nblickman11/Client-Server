import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Alphabetizer {

	TreeSet<String> treeSetSorted = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);

	public void addToAlphabetizedSet(String line) {
		treeSetSorted.add(line);
	}

	public List<String> getAlphabetizedLines() {
		List<String> sortedFileLines = new ArrayList<String>();
		sortedFileLines.addAll(treeSetSorted);
		return sortedFileLines;
	}
}
