import java.io.FileWriter;
import java.io.IOException;

/*
Ali Hilal, CS 2336, Project 4, Redbox
Binary tree class
 */

public class BinaryTree <T>{

 T root;

 BinaryTree(){}
 BinaryTree(T mov){
     Node newMovie = new Node((Movie) mov);
     if (root == null)
         this.root = (T) newMovie;
 }

 //adds movie to tree (position determined by alphabetical order)
     public void addMovie(Movie keyMovie, Node target){
         Node newMovie = new Node(keyMovie);
         if(target == null){
             target = (Node) root;
         }
         if (root == null){
             root = (T) newMovie;

         }

         else {

         Node parent;

                 parent = target;
                 if ((keyMovie.getName().compareToIgnoreCase(target.movieKey.getName())) < 0){
                     target = target.leftChild;

                     if (target == null){
                         parent.leftChild = newMovie;
                         return;
                     }
                 }
                 else {
                     target = target.rightChild;

                     if (target == null){
                         parent.rightChild = newMovie;
                         return;
                     }
                 }
             addMovie(keyMovie, target);
         }



     }

//searches tree for nade with the movie title passes in, returns the node

     public Node searchTree(String movieName, Node target){

         if (target == null)
             target = (Node) root;

         Node searchNode = null;

         if (movieName.equalsIgnoreCase(target.movieKey.getName()))
             return target;

         if(target.leftChild != null && searchNode == null)
             searchNode = searchTree(movieName ,target.leftChild);

         if(target.rightChild != null && searchNode == null)
             searchNode = searchTree(movieName, target.rightChild);



         return searchNode;
     }
//if the title is there, movie is removed and method returns true, otherwise the method returns false
     public boolean removeMovie(String movieName){
         Node target = (Node) root;
         Node parent = (Node) root;
         boolean isItALeftChild = true;
         while (!movieName.equalsIgnoreCase(target.movieKey.getName())) {
             parent = target;

             if ((movieName.compareToIgnoreCase(target.movieKey.getName())) < 0) {

                 isItALeftChild = true;
                 target = target.leftChild;

             } else {
                 isItALeftChild = false;
                 target = target.rightChild;

             }

             if (target == null)
                 return false;

         }

         if (target.leftChild == null && target.rightChild == null) {

             if (target == root)
                 root = null;

             else if (isItALeftChild)
                 parent.leftChild = null;
             else
                 parent.rightChild = null;

         }
         else if (target.rightChild == null) {

             if (target == root)
                 root = (T) target.leftChild;

             else if (isItALeftChild)
                 parent.leftChild = target.leftChild;

             else
                 parent.rightChild = target.leftChild;

         }

         else if (target.leftChild == null) {

             if (target == root)
                 root = (T) target.rightChild;
             else if (isItALeftChild)
                 parent.leftChild = target.rightChild;
             else
                 parent.rightChild = target.rightChild;
         }

         else {
             Node replacement = getReplacementNode(target);
             if (target == root)
                 root = (T) replacement;

             else if (isItALeftChild)
                 parent.leftChild = replacement;
             else
                 parent.rightChild = replacement;
             replacement.leftChild = target.leftChild;
         }

         return true;

     }

//method to basically moves children nodes to appropriate spot when parent is removed
    private Node getReplacementNode(Node replacedNode) {

        Node replacementParent = replacedNode;
        Node replacement = replacedNode;
        Node focusNode = replacedNode.rightChild;

        while (focusNode != null) {
            replacementParent = replacement;
            replacement = focusNode;
            focusNode = focusNode.leftChild;

        }

        if (replacement != replacedNode.rightChild) {
            replacementParent.leftChild = replacement.rightChild;
            replacement.rightChild = replacedNode.rightChild;

        }
        return replacement;
    }

    public void printTree(Node target, int x, FileWriter myWriter) {
        try {

            if (x == 0) {
                myWriter.write("Title");
                myWriter.write(String.format("%43s", "Number rented"));
                myWriter.write(String.format("%38s", "Number available\n"));
                myWriter.write("--------------------------------------------------------------------------------------\n");
            }
            if (target != null) {
                printTree(target.leftChild, 1, myWriter);
                myWriter.write(target.movieKey.printMovieInfo());
                printTree(target.rightChild, 1, myWriter);
            }


        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }

    }

}
