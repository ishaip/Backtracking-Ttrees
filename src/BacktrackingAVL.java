import java.util.List;

public class BacktrackingAVL extends AVLTree {

	//You are to implement the function Backtrack.
    public void Backtrack() {
        if(!nodeAdded.isEmpty()){
            Backtrack(nodeAdded.removeFirst(), nodeRotated.removeFirst(), rotationKind.removeFirst(), root);
        }
    }

    public Node Backtrack(int valueToRemove, int valueRotated, int rotationKind, Node node) {
        if (valueToRemove == node.value) {
            System.out.println(nodeAdded.size()+" " + nodeRotated.size() +" " + this.rotationKind.size());
            if (node.parent.left == node)
                node.parent.left = null;
            else
                node.right = null;
            return null;
        }

        else {
            if (valueToRemove < node.value) {
                node.left = Backtrack(valueToRemove, valueRotated, rotationKind, node.left);
                if (node.left != null)
                    node.left.parent = node;
            }
            else {
                node.right = Backtrack(valueToRemove, valueRotated, rotationKind, node.right);
                if (node.right != null)
                    node.right.parent = node;
            }

            node.height = Math.max(getNodeHeight(node.left), getNodeHeight(node.right)) + 1;

            if (rotationKind != 0 && node.value == valueRotated) {

                // were Left Cases
                if (rotationKind > 0) {
                    if (valueToRemove > node.left.value) {
                        node.left = rightRotate(node.left);
                    }
                    System.out.println(node.value + " am here");
                    node.right = leftRotate(node.right);
                }


                // Right Cases
                else if (rotationKind > 0) {
                    if (valueToRemove < node.right.value) {
                        node.right = leftRotate(node.right);

                    }
                    System.out.println(node.value + " am here");
                    node = rightRotate(node);
                }
            }
        }
        return node;
    }

    public Node Serch(int value){
        Node current = this.root;
        while(current != null &&current.value != value){
            if (current.value > value)
                current = current.left;
            else
                current = current.right;
        }
        return current;
    }
    
    //Change the list returned to a list of integers answering the requirements
    public static List<Integer> AVLTreeBacktrackingCounterExample(){
        IntegrityStatement.signature(); // Reminder!
        throw new UnsupportedOperationException("You should implement this");
    }
}
