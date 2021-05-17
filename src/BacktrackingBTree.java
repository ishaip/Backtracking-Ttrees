import java.util.LinkedList;
import java.util.List;

public class BacktrackingBTree<T extends Comparable<T>> extends BTree<T> {

	//You are to implement the function Backtrack.
	public void Backtrack() {
	    IntegrityStatement.signature(); // Reminder!
		T[] arr = (T[]) new Object[5];
	}
	
	//Change the list returned to a list of integers answering the requirements
	public static List<Integer> BTreeBacktrackingCounterExample(){
		LinkedList<Integer> output = new LinkedList<Integer>();
		output.addLast(3);
		output.addLast(4);
		output.addLast(6);
		output.addLast(8);
		output.addLast(9);
		output.addLast(7);
		return output;
	}
}
