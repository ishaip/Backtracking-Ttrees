import java.util.LinkedList;
import java.util.List;

public class BacktrackingBTree<T extends Comparable<T>> extends BTree<T> {

	//You are to implement the function Backtrack.
	public void Backtrack() {
		if ( !added.isEmpty() ){
			T value = added.removeFirst();
			Node<T> valNode = getNode(value); // searching the node which we remove from

			int number = numOfSplited.removeFirst(); // the number of nodes that were splitted under the insertion
			Node<T>[] toBeMergeArray = new Node[number]; // initializing an array of all the nodes we have to remerge
			for (int i=0; i<number; i++){ // removing the nodes above from the deque
				toBeMergeArray[i] = splited.removeFirst();
			}

			Node<T> current = root;
			int medianIndex = toBeMergeArray[number - 1].numOfKeys / 2; // the index of the middle of the first node we need to merge
			T medianValue = toBeMergeArray[number - 1].getKey(medianIndex); // the value(key) of the middle of the first node we need to merge
			int counter = 2;

			while ( current != null && (current.equals(root) || !current.parent.equals(valNode)) ){ // iterating until we get to node we remove the key from
				if ( number >= counter && current.indexOf(medianValue) != -1 ){ // it means that 'current' is a node that should be remerged
					BacktrackSplit(current, toBeMergeArray[number - counter + 1], medianValue);
					medianIndex = toBeMergeArray[number - counter + 1].numOfKeys / 2;
					medianValue = toBeMergeArray[number - counter].getKey(medianIndex);
					counter = counter + 1;
				}
				int index = getValuePosition(value, current);
				current = current.getChild(index);
			}
			valNode.removeKey(value); // removing the key we need so
		}
    }

    private void BacktrackSplit(Node<T> parent, Node<T> splitted, T median){ // merge again a splitted node
		int index = parent.indexOf(median);

		parent.removeChild(index);
		parent.removeChild(index);
		parent.removeKey(median);
		parent.addChild(splitted);

		if ( root.numOfKeys == 0 )
			root = splitted;
	}

	private int getValuePosition (T value, Node node) {
		boolean found = false;
		int i=0;
		while(i < node.getNumberOfKeys() && !found) {
			if(value.compareTo((T) node.getKey(i)) <= 0){
				found = true;
			} else {
				i++;
			}
		}

		return i;
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
