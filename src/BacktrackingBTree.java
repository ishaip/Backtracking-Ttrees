import java.util.LinkedList;
import java.util.List;

public class BacktrackingBTree<T extends Comparable<T>> extends BTree<T> {

	//You are to implement the function Backtrack.
	public void Backtrack() {
		if ( !added.isEmpty() ){
			T value = added.removeFirst();
			int number = numOfSplited.removeFirst();
			Node<T>[] toBeMergeArray = new Node[number];
			for (int i=0; i<number; i++){
				toBeMergeArray[i] = splited.removeFirst();
			}
			Node<T> current = root;
			int medianIndex = toBeMergeArray[number - 1].numOfKeys/2;
			T medianValue =  toBeMergeArray[number - 1].getKey(medianIndex);
			int counter = 2;
			while(current.indexOf(value) != 1){
				if( current.indexOf(medianValue) == -1) {
					BacktrackSplit(current, toBeMergeArray[number - counter + 1],medianValue);
					medianIndex = toBeMergeArray[number - counter].numOfKeys/2;
					medianValue =  toBeMergeArray[number - counter].getKey(medianIndex);
					counter = counter + 1;
				}
				int index = getValuePosition(value, current);
				current = current.getChild(index);
			}
			current.removeKey(value);
		}
    }


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

    private void BacktrackSplit(Node<T> parent,Node<T> splitted , T median){
		int index = parent.indexOf(median);
		parent.removeChild(index);
		parent.removeChild(index + 1);
		parent.removeKey(median);
		parent.addChild(splitted);
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
