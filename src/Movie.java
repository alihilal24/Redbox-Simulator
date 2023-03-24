/*
Ali Hilal, CS 2336, Project 4, Redbox
Movie object class, holds the move name, amount rented and amount available.
 */
public class Movie {
    String name;
    int rented;
    int available;

    public Movie (String nameVal, int rentedVal, int availableVal){
        this.name = nameVal;
        this.rented = rentedVal;
        this.available = availableVal;
    }

    public int getRented() {
        return rented;
    }

    public int getAvailable() {
        return available;
    }

    public String getName() {
        return name;
    }

    //adds one to rented number, takes one from available number
    public void movieRented(){
        this.rented++;
        this.available--;
    }
    //adds one to available number, takes one from rented number

    public  void movieReturned(){
        this.rented--;
        this.available++;
    }
    public String printMovieInfo(){

        String nameAndSpaces = name;
        String rentedAndSpaces = rented + "";
        String availableAndSpaces = available + "";
        int spaces = 35 - name.length();
        for (int i = 0; i < spaces; i++)
            nameAndSpaces += " ";
        spaces = 35 - rentedAndSpaces.length();
        for (int i = 0; i < spaces; i++)
            rentedAndSpaces += " ";
        spaces = 35 - availableAndSpaces.length();
        for (int i = 0; i < spaces; i++)
            availableAndSpaces += " ";
        return (nameAndSpaces + rentedAndSpaces + availableAndSpaces + "\n");
    }
}
