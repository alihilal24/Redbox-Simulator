
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void AddTitleTest() throws IOException {
        String movieName = "Cars";
        int rented = 4;
        int available = 6;

        Movie movie = new Movie(movieName, rented, available);
        BinaryTree expected = new BinaryTree(movie);
        BinaryTree actual = new BinaryTree();
        actual.addMovie(movie,null);

        if (actual.root != expected.root) {
            assertTrue("Movie added", true);
        } else {
            assertTrue("Movie not added", false);
        }

    }

    @Test
    public void RemoveTitleTest() {
        String movieName = "Cars";
        int rented = 4;
        int available = 6;

        Movie movie = new Movie(movieName, rented, available);
        BinaryTree expected = new BinaryTree(null);
        BinaryTree actual = new BinaryTree();
        actual.addMovie(movie,null);
        actual.removeMovie("Cars");

        if (actual.root != expected.root) {
            assertTrue("Movie removed", true);
        } else {
            assertTrue("Movie not removed", false);
        }

    }

    @Test
    public void RentTitleTest() {
        Movie expected = new Movie("Cars", 3, 4);
        Movie actual = new Movie("Cars", 2, 5);
        actual.movieRented();
        if (actual.getRented() != expected.getRented()) {
            assertTrue("Rented value different", false);
        } else {
            assertTrue("Rented value correct", true);
        }
    }

    @Test
    public void ReturnTitleTest() {
        Movie expected = new Movie("Cars", 2, 5);
        Movie actual = new Movie("Cars", 3, 4);
        actual.movieReturned();
        if (actual.getAvailable() != expected.getAvailable()) {
            assertTrue("Available value different", false);
        } else {
            assertTrue("Available value correct", true);
        }
    }
}


