/**
 * Class represents Binary Search Tree.
 */
public class BSTree {
    private BSNode root;
    private static int counter = 0;

    /**
     * Constructor.
     * @param arr array of values for insert the new tree.
     */
    public BSTree(Point arr[])
    {
        this.root = sortedArrayToTree(arr, 0, arr.length-1, null); //recursive function for inserting the values to the tree by linear time.
        insertSizeAndSum(root); //calculate and insert the size and ySum values for every node.
    }

    /**
     * Get function for the tree node.
     * @return root node.
     */
    public BSNode getRoot()
    {
        return this.root;
    }

    /**
     * recursive function for inserting the values to the tree by linear time.
     * set the node in every level to be the middle.
     * @param arr sorted values for inserting the tree.
     * @param start index which start insert from, used for the recursive function.
     * @param end index which end insert to, used for the recursive function.
     * @param parent the parent of the set node.
     * @return
     */
    private BSNode sortedArrayToTree(Point arr[], int start, int end, BSNode parent) {
        if (start > end) return null; //stopping condition.
        int mid = (start + end) / 2;
        BSNode node = new BSNode(arr[mid]); //set the node to be the mid.
        node.parent = parent;
        node.left = sortedArrayToTree(arr, start, mid-1, node); //set the left child recursively.
        node.right = sortedArrayToTree(arr, mid+1, end, node); //set the right child recursively.
        return node;
    }

    /**
     * Function for insert new point.
     * @param p val for insert.
     */
    public void insert(Point p)
    {
        if(root == null){ //if the tree is empty.
            root = new BSNode(p); //create a new tree.
            root.size = 1;
            root.ySum = p.getY();
            root.parent = null;
        }
        else{
            insert(p, root); //else, insert for the root sub-tree.
        }
    }

    /**
     * Function for insert data recursively.
     * @param p val for insert.
     * @param bsNode to insert to.
     */
    private void insert(Point p, BSNode bsNode)
    {
        //need to change the node parameters, because inserted his sub-tree.
        bsNode.size++;
        bsNode.ySum += p.getY();
        if (p.getX() < bsNode.data.getX())
        {
            if(bsNode.left == null) //create a new node, and set it to be the left child.
            {
                BSNode temp =new BSNode(p);
                temp.size = 1;
                temp.ySum = p.getY();
                temp.parent = bsNode;
                bsNode.left = temp;
            }
            else
                insert( p, bsNode.left ); //insert to the left child recursively.
        }
        else
        {
            if(bsNode.right == null) { //create a new node, and set it to be the right child.
                BSNode temp =new BSNode(p);
                temp.size = 1;
                temp.ySum = p.getY();
                temp.parent = bsNode;
                bsNode.right = temp;
            }
            else
                insert( p, bsNode.right ); //insert to the right child recursively.
        }
    }

    /**
     * Functions to search if element is exists.
     * @param val to search for.
     * @return if the value exists.
     */
    public boolean search(int val)
    {
        return search(root, val);
    }

    /**
     * Recursive function to search if element is exists.
     * @param bsNode to search in his sub-tree.
     * @param val to search for.
     * @return if the value exists.
     */
    private boolean search(BSNode bsNode, int val)
    {
        boolean found = false;
        while ((bsNode != null) && !found) //while there is more to search and didn't found.
        {
            int nodeVal = bsNode.data.getX();
            if (val < nodeVal)
                bsNode = bsNode.left;
            else if (val > nodeVal)
                bsNode = bsNode.right;
            else //if the values is equal.
            {
                found = true;
                break;
            }
            found = search(bsNode, val);
        }
        return found;
    }

    /**
     * Function to get the node with some value.
     * @param val value to search.
     * @return the BSNode with the value.
     */
    public BSNode getNode(int val)
    {
        if(root == null) //if the tree is empty.
            return null;
        if(search(val) == true)
            return getNode(root, val);
        else
            return null;
    }

    /**
     * Recursive function to get node from sub-tree.
     * @param bsNode to search in his sub-tree.
     * @param val to get.
     * @return the node with the requested val.
     */
    private BSNode getNode(BSNode bsNode, int val)
    {
        int nodeVal = bsNode.data.getX();
        if (val < nodeVal)
            return getNode(bsNode.left, val);
        else if (val > nodeVal)
            return getNode(bsNode.right, val);
        else
            return bsNode;
    }

    /**
     * Get the closest node with smaller value.
     * @param val of the requested node.
     * @return the closest node.
     */
    public BSNode getClosestNodeFromLeft (int val)
    {
        return getClosestNodeFromLeft(null,getRoot(),val);
    }

    /**
     * Recursive function to get the closest node from left.
     * @param curr closest node already found until now.
     * @param bsNode to search in his sub-tree.
     * @param val of the requested node.
     * @return the closest node.
     */
    private BSNode getClosestNodeFromLeft(BSNode curr, BSNode bsNode, int val) {
        if(bsNode == null) //stopping condition, finished to search.
            return curr; //the node that founded until now, that means there is no closer one.
        if (bsNode.data.getX() == val) //if the exact val founded.
            return bsNode;
        else
            if(val< bsNode.data.getX())
                return getClosestNodeFromLeft(curr,bsNode.left, val);
            else
            {
                curr= bsNode;
                return getClosestNodeFromLeft(curr,bsNode.right,val);
            }
    }

    /**
     * Get the closest node with bigger value.
     * @param val of the requested node.
     * @return the closest node.
     */
    public BSNode getClosestNodeFromRight (int val)
    {
        return getClosestNodeFromRight(null,getRoot(),val);
    }

    /**
     * Recursive function to get the closest node from right.
     * @param curr closest node already found until now.
     * @param bsNode to search in his sub-tree.
     * @param val of the requested node.
     * @return the closest node.
     */
    private BSNode getClosestNodeFromRight(BSNode curr, BSNode bsNode, int val) {
        if(bsNode == null) //stopping condition, finished to search.
            return curr; //the node that founded until now, that means there is no closer one.
        if (bsNode.data.getX() == val) //if the exact val founded.
            return bsNode;
        else
        if(val> bsNode.data.getX())
            return getClosestNodeFromRight(curr,bsNode.right, val);
        else
        {
            curr= bsNode;
            return getClosestNodeFromRight(curr,bsNode.left,val);
        }
    }

    /**
     * Remove node with some value.
     * @param val to remove.
     */
    public void remove (int val){
        BSNode bsNode = getNode(val);
        if (bsNode == null) return; //if there is no node with such val.
        else remove(getNode(val).data,root);
    }

    /**
     * Recursive function to remove a point from the tree.
     * @param point to remove.
     * @param bsNode to search in his sub-tree.
     */
    private void remove (Point point, BSNode bsNode)
    {
        if (point.getX() < bsNode.data.getX()){
            bsNode.size--;
            bsNode.ySum = bsNode.ySum - point.getY();
            remove (point, bsNode.left);
        }
        else if (point.getX() > bsNode.data.getX()){
            bsNode.size--;
            bsNode.ySum = bsNode.ySum - point.getY();
            remove (point, bsNode.right);
        }
        else { //if the node with the point to remove has found.
            if (bsNode.left != null && bsNode.right != null) //if it has two childes.
            {
                if(bsNode.right.size > bsNode.left.size){ //get the val from the right sub-tree.
                    BSNode minFromRight = getMin(bsNode.right); //the next val by the sorted order.
                    //replace the value of the nodes.
                    bsNode.data = minFromRight.data;
                    bsNode.size--;
                    bsNode.ySum = bsNode.ySum - minFromRight.data.getY();
                    remove (minFromRight.data, bsNode.right); //remove the duplicate node.
                }
                else{ //the same like the right from the left side.
                    BSNode maxFromLeft = getMax(bsNode.left);
                    bsNode.data = maxFromLeft.data;
                    bsNode.size--;
                    bsNode.ySum = bsNode.ySum - maxFromLeft.data.getY();
                    remove (maxFromLeft.data, bsNode.left);
                }
            }
            else if(bsNode.left != null) {//if it has one child, left one.
                if(bsNode == root)
                    root = bsNode.left;
                else if(bsNode.parent.left == bsNode)
                    bsNode.parent.left = bsNode.left;
                else
                    bsNode.parent.right = bsNode.left;
                bsNode.left.parent = bsNode.parent; //set the parent of the replaced node, to be the parent of the removed one.
                bsNode.left = null;
            }
            else if(bsNode.right != null) { //if it has one child, right one.
                if(bsNode == root)
                    root = bsNode.right;
                else if(bsNode.parent.left == bsNode)
                    bsNode.parent.left = bsNode.right;
                else
                    bsNode.parent.right = bsNode.right;
                bsNode.right.parent = bsNode.parent;
                bsNode.right = null;
            }
            else { //if there is no childes.
                if(bsNode == root) //if the node for remove is the root.
                    root = null;
                else if(bsNode.parent.left == bsNode) //if the node to remove is the left child of his parent.
                    bsNode.parent.left = null; //disconnect it from the tree.
                else
                    bsNode.parent.right = null;
            }
        }
    }

    /**
     * Get the location of the value by the sorted order.
     * @param search val to search for.
     * @return the location of that value.
     */
    public int getLocationByNode (int search)
    {
        return getLocationByNode(this.getRoot(),search,0);
    }

    /*
    Recursive function to get the location of the value by the sorted order.
     */
    private int getLocationByNode (BSNode bsNode, int search, int num)
    {
        int size;
        if(bsNode.left == null) {
            size = 0;
        }
        else {
            size = bsNode.left.size;
        }
        if(search == bsNode.data.getX())
        {
            return num + size + 1;
        }
        if(search < bsNode.data.getX())
            return getLocationByNode(bsNode.left,search,num);
        else {
            return getLocationByNode(bsNode.right, search, num + size +1);
        }
    }

    /**
     * Get the sum of the Ys between the first val to the requested val, include.
     * @param search val for search.
     * @return the sum.
     */
    public int getSumbyNode(int search)
    {
        return getSumbyNode(this.getRoot(),search, 0);
    }

    /*
    Recursive function to get the sum of the Ys between the first val to the requested val, include.
     */
    private int getSumbyNode(BSNode bsNode, int search, int sum)
    {
        int currSum;
        if(bsNode.left == null) {
            currSum = 0;
        }
        else {
            currSum= bsNode.left.ySum;
        }
        if(search == bsNode.data.getX())
        {
            return sum + currSum +bsNode.data.getY();
        }
        if(search < bsNode.data.getX())
            return getSumbyNode(bsNode.left,search,sum);
        else {
            return getSumbyNode(bsNode.right, search, sum + currSum + bsNode.data.getY());
        }
    }

    /**
     * Get the points between the requested values, sorted at inorder, include.
     * @param arr to insert the points to.
     * @param XLeft left domain.
     * @param XRight right domain.
     * @return the full array.
     */
    public Point[] inorder(Point[] arr, int XLeft, int XRight)
    {
        inorder(arr, root,XLeft,XRight);
        counter = 0; //set the counter to 0, for the next time.
        return arr;
    }

    /*
    Recursive function to get the points between the requested values, sorted at inorder, include.
     */
    private void inorder(Point[] arr, BSNode bsNode, int XLeft, int XRight)
    {
        if (bsNode != null) //if not finished to get the points.
        {
            if(XLeft < bsNode.data.getX()) //if the curr point bigger from the left domain.
                inorder(arr, bsNode.left, XLeft, XRight);
            if(bsNode.data.getX() >= XLeft && bsNode.data.getX() <= XRight) { //if the node is in the domain.
                arr[counter] = bsNode.data;
                counter++;
            }
            if(XRight > bsNode.data.getX()) //if the curr point smaller from the left domain.
                inorder(arr, bsNode.right, XLeft, XRight);
        }
    }

    /**
     * Recursive function to insert the ySum and size values for the first time after creating the tree.
     * @param node to insert his sub-tree.
     */
    private void insertSizeAndSum(BSNode node)
    {
        int counter=0, sum=0;
        if(node.left !=null) {
            insertSizeAndSum(node.left);
            counter += node.left.size;
            sum +=node.left.ySum;
        }
        if(node.right !=null) {
            insertSizeAndSum(node.right);
            counter += node.right.size;
            sum +=node.right.ySum;
        }
        node.size = counter +1;
        node.ySum = sum + node.data.getY();
    }

    /**
     * Get the node of the max value in the sub-tree of node.
     * @param bsNode to search in it sub-tree.
     * @return the node has the max value.
     */
    public BSNode getMax(BSNode bsNode){
        if(bsNode.right == null){
            return bsNode;
        }
        else{
            return getMax(bsNode.right);
        }
    }

    /**
     * Get the node of the min value in the sub-tree of node.
     * @param bsNode to search in it sub-tree.
     * @return the node has the min value.
     */
    public BSNode getMin(BSNode bsNode){
        if(bsNode.left == null){
            return bsNode;
        }
        else{
            return getMin(bsNode.left);
        }
    }


}
