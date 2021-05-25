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
            //System.out.println(nodeAdded.size()+" " + nodeRotated.size() +" " + this.rotationKind.size());
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
                // back from Left Cases
                if (rotationKind > 0) {
                    if(node == root)
                        root = leftRotate(node);
                    else
                        node = leftRotate(node);
                    if (rotationKind == 2) {
                        node.left = rightRotate(node.left);
                    }
                }
                // back from Right Cases
                else {
                    if(node == root)
                        root = rightRotate(node);
                    else
                        node = rightRotate(node);
                    if (rotationKind  == -2) {
                        node.right = leftRotate(node.right);
                    }
                }
            }
        }
        return node;
    }

    public Node BRightRotate(Node y){
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        //Update parents
        if(T2 != null) {
            T2.parent = y;
        }

        x.parent = y.parent;
        y.parent = x;
        // Return new root
        return x;
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
