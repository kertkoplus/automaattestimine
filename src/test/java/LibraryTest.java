/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryTest {
   
    
    @Test public void testTemperatureAtTheMoment {
    	Library testClass = new Library();
    	assertEquals(25, testClass.temperatureAtTheMoment());
    }
    
    @Test public void testHighestTemperature {
    	Library testClass = new Library();
    	assertEquals(30, testClass.highestTemperature());
    }
    
    @Test public void testLowestTemperature {
    	Library testClass = new Library();
    	assertEquals(-25, testClass.lowestTemperature());
    }
    
    @Test public void testCoordinates {
    	Library testClass = new Library();
    	assertEquals('xxx:yyy', testClass.coordinates());
    }
    
    
}