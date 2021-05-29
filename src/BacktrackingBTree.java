import java.util.LinkedList;
import java.util.List;

public class BacktrackingBTree<T extends Comparable<T>> extends BTree<T> {

	//You are to implement the function Backtrack.
	public void Backtrack() {
		if ( !added.isEmpty() ){		//there were no inserts
			T value = added.removeFirst();
			int number = numOfSplited.removeFirst();
			Node<T>[] toBeMergeArray = new Node[number];
			for (int i=0; i<number; i++){
				toBeMergeArray[i] = splited.removeFirst();
			}
			Node<T> current = root;
			int medianIndex = -1;
			T medianValue = null;
			int counter = 1;		//start with defult values
			if(number !=0 ){
				medianIndex = toBeMergeArray[number - counter].numOfKeys/2;
				medianValue =  toBeMergeArray[number - counter].getKey(medianIndex);		//if there are nodes to merge
			}
			while(current != null && current.indexOf(value) == -1 ){
				if(!(number == counter -1) && current.indexOf(medianValue) != -1) {			//will happen if the node was splitted
					medianIndex = toBeMergeArray[number - counter].numOfKeys/2;
					medianValue =  toBeMergeArray[number - counter].getKey(medianIndex);
					BacktrackSplit(current, toBeMergeArray[number - counter],medianValue);
					counter = counter + 1;													//start searching for the next split
				}
				int index = getValuePosition(value, current);
				current = current.getChild(index);
			}
			if (current != null && getValuePosition(value, current) != -1)
				current.removeKey(value);													// remove the value that was inserted
		}
    }

    //very similar to the one in the original BTree
	private int getValuePosition(T value, Node node) {
		boolean found = false;
		int i = 0;
		while(i < node.getNumberOfKeys() && !found) {
			if(value.compareTo((T) node.getKey(i)) <= 0){
				found = true;
			} else {
				i++;
			}
		}
		return i;
	}

	// a backtrack from a split
    private void BacktrackSplit(Node<T> parent,Node<T> splitted , T median){
		int index = parent.indexOf(median);
		parent.removeChild(index);
		parent.removeChild(index);
		parent.removeKey(median);
		parent.addChild(splitted);
		if (root.numOfKeys == 0)		//if we splitted the root
			root =splitted;
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
