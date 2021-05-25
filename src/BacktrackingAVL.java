import java.util.LinkedList;
import java.util.List;

public class BacktrackingAVL extends AVLTree {

	//You are to implement the function Backtrack.
    public void Backtrack() {
        if ( !nodeAdded.isEmpty() ){
            Backtrack(nodeAdded.removeFirst(), nodeRotated.removeFirst(), rotationKind.removeFirst(), root);
        }
    }

    public Node Backtrack (int valueToRemove, int valueRotated, int rotateKind, Node node){
        if ( nodeAdded.isEmpty() ){ //it means that we remove the root
            root = null;
            return null;
        }
        if (valueToRemove == node.value){
            if (node.parent.left == node) //node is left child
                node.parent.left = null;
            else                         //node is right child
                node.parent.right = null;
            return null;
        }
        else if (valueToRemove < node.value) {
            if (node.left != null){
                node.left = Backtrack(valueToRemove, valueRotated, rotateKind, node.left);
                if (node.left != null)
                    node.left.parent = node; //why is needed?
            }
        }
        else {
            if (node.right != null){
                node.right = Backtrack(valueToRemove, valueRotated, rotateKind, node.right);
                if (node.right != null)
                    node.right.parent = node; //why is needed?
            }
        }

        /* 2. Update height of this ancestor node */
        node.height = Math.max(getNodeHeight(node.left), getNodeHeight(node.right)) + 1;

        if (rotateKind != 0 && node.value == valueRotated){
            if (rotateKind > 0 ) {
                if (node == root)
                    root = leftRotate(node);
                else
                    node = leftRotate(node);
                if (rotateKind == 2)
                    node.left = rightRotate(node.left); //left-right (rotationKind = 2)

            }
            else {
                if (node == root)
                    root = rightRotate(node);
                else
                    node = rightRotate(node);
                if (rotateKind == -2)
                    node.right = leftRotate(node.right); //right-left (rotationKind = -2)

            }
        }

        return node;
    }
    
    //Change the list returned to a list of integers answering the requirements
    public static List<Integer> AVLTreeBacktrackingCounterExample(){
        List<Integer> ans = new LinkedList<>();
        ans.add(1);
        ans.add(2);
        ans.add(3);
        return ans;
    }
}
