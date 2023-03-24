import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
Ali Hilal, CS 2336, Project 4, Redbox
Main method found here. Files are imported here, information is written to apporpriate variables. commands are in the
form of a switch. errors in transaction file are written to error.txt, otherwise infromation is written in a neat table-like
manner to RedboxKoisk.txt file.
 */

public class Main {
    public static void main (String args[]) throws IOException {
        BinaryTree movieBST = new BinaryTree();

        while (ScannerFactory.InventoryGetScannerInstance().hasNextLine()){
            String line = ScannerFactory.InventoryGetScannerInstance().nextLine();
            String linearr[] = line.split(", ", 3);
            Movie movie = new Movie(linearr[0], Integer.parseInt(linearr[1]), Integer.parseInt(linearr[2]));
            movieBST.addMovie(movie, null);
        }
        ScannerFactory.CloseScannerInstance();
        File errorFile = new File("Error.txt");
        FileWriter errorWriter = new FileWriter(errorFile);
        while (ScannerFactory.TransactionGetScannerInstance().hasNextLine()){
            String line = ScannerFactory.TransactionGetScannerInstance().nextLine();
            String arrline[] = line.split(",", 3);
            String command = arrline[0];
            String commandSplit[] = command.split(" ", 2);
            command = commandSplit[0];
            String movieCommanded = commandSplit[1];
            command = command.toLowerCase();
            movieCommanded = movieCommanded.substring(1, movieCommanded.length() - 1);
            Node target;

            switch (command){

                case "add":
                    int numToAdd = Integer.parseInt(arrline[1].replaceAll(" ", ""));
                    target = movieBST.searchTree(movieCommanded, null);
                    if (target == null){
                        Movie newMovie = new Movie(movieCommanded, 0, numToAdd);
                        movieBST.addMovie(newMovie, null);
                    }
                    else {
                        target.movieKey.available += numToAdd;
                    }
                    break;

                case "remove":
                    int numtoRemove =  Integer.parseInt(arrline[1].replaceAll(" ", ""));
                    target = movieBST.searchTree(movieCommanded, null);
                    target.movieKey.available -= numtoRemove;
                    if (target.movieKey.available < 0)
                        target.movieKey.available = 0;
                    if ((target.movieKey.available == 0) && (target.movieKey.rented == 0))
                        movieBST.removeMovie(movieCommanded);
                    break;

                case "rent":
                    target = movieBST.searchTree(movieCommanded, null);
                    target.movieKey.movieRented();
                    break;

                case "return":
                    target = movieBST.searchTree(movieCommanded, null);
                    target.movieKey.movieReturned();
                    break;

                default:
                    errorWriter.write(line + "\n");
            }

        }

        File outputFile = new File("RedboxKiosk.txt");
        FileWriter myWriter = new FileWriter(outputFile);
        movieBST.printTree((Node) movieBST.root, 0, myWriter);
        myWriter.close();
        errorWriter.close();

        }




    }

