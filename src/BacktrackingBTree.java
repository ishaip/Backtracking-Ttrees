import java.util.LinkedList;
import java.util.List;

public class BacktrackingBTree<T extends Comparable<T>> extends BTree<T> {

	//You are to implement the function Backtrack.
	public void Backtrack() {
		if ( !added.isEmpty() ){
			T value = added.removeFirst();
			int number = numOfSplited.removeFirst();
			T[] toBeMergeArray = (T[]) new Object[number];
			for (int i=0; i<number; i++){
				toBeMergeArray[i] = splited.removeFirst();
			}
			// remove(Value); //Todo: add a method remove to BacktrackBTree

		}
    }
	
	//Change the list returned to a list of integers answering the requirements
	public static List<Integer> BTreeBacktrackingCounterExample(){
		LinkedList<Integer> ans = new LinkedList<Integer>();
		ans.addLast(3);
		ans.addLast(4);
		ans.addLast(6);
		ans.addLast(8);
		ans.addLast(9);
		ans.addLast(7);
		return ans;
	}
}
