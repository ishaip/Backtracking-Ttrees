import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

public class BacktrackingAVL extends AVLTree {

	//You are to implement the function Backtrack.
    public void Backtrack() {
        if(!nodeAdded.isEmpty()){       //if there were no Nodes added
            int nodeToRemove = nodeAdded.removeFirst();
            int valueRotated = nodeRotated.removeFirst();
            int rotationValue = rotationKind.removeFirst();
            if (!nodeAdded.isEmpty()) {  //if the only thing added was the root
                if (rotationValue != 0)       //if there wer no rotaions the only thing needed is to remove a node
                    Backtrack(nodeToRemove, valueRotated, rotationValue, root);
                remove(nodeToRemove, root);
            }
            else
                root = null;
        }
    }

    //recursion delete node, seen in intro to CS with the added function of updating the heights of the nodes
    private void remove (int valueToRemove, Node node){
        if (valueToRemove == node.value) {
            if (node.parent.left == node)
                node.parent.left = null;
            else
                node.parent.right = null;
        }
        else {
            if (valueToRemove < node.value) {
                if (node.left != null)
                    remove(valueToRemove, node.left);
            }
            else {
                if (node.right != null)
                    remove(valueToRemove, node.right);
            }
        }
        node.height = Math.max(getNodeHeight(node.left), getNodeHeight(node.right)) + 1;
    }

    private Node Backtrack(int valueToRemove, int valueRotated, int rotationKind, Node node) {
        if (node == null)
            return null;        //break
        // similar to the insert we will serch for where the rotations happened
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

        //a mirror of the insert command in the AVL
        if (node.value == valueRotated) {
            // back from Left Cases
            if (rotationKind > 0) {
                if(node == root)
                    root = leftRotate(node);
                else
                    node = leftRotate(node);
                if (rotationKind == 2) {        //backing from a double rotation
                    node.left = rightRotate(node.left);
                }
            }
            // back from Right Cases
            else {
                if(node == root)
                    root = rightRotate(node);
                else
                    node = rightRotate(node);
                if (rotationKind  == -2) {  //backing from a double rotation
                    node.right = leftRotate(node.right);
                }
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
