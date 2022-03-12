import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class StockManagementTests {

    ExternalISBNDataService testWebService;
    ExternalISBNDataService testDatabaseService;
    StockManager stockManager;

    @BeforeEach // tutorial uses @Before, because it's using different versions of JUnit and Mockito
    public void setup() {
        testWebService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        testDatabaseService = mock(ExternalISBNDataService.class);
        stockManager.setDatabaseService(testDatabaseService);
    }

    @Test
    public void testCanGetACorrectLocatorCode() {

        when(testWebService.lookup(anyString())).thenReturn(new Book ("0140177396", "Of Mice And Men", "J. Steinbeck"));
        when(testDatabaseService.lookup(anyString())).thenReturn(null);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {

        // tell mockito what to return when a specific method of mocked class is called
        when(testDatabaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abd","abc"));

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        // verify that in my class this method was called int (?) times using these parameters (params)
        // we don't care what the return values are, we are verifying how many times was this method called
        // default of times(?) is 1, you can skip the times() altogether in that case:
        // verify(databaseService).lookup("0140177396");
        verify(testDatabaseService, times(1)).lookup("0140177396");


        // we don't care what string is used, we just want to know how many times the method was used
        // you can use never() instead of times(0), you can also use atLeast(int) and atMost(int):
        // verify(webService, never()).lookup(anyString());
        verify(testWebService, times(0)).lookup(anyString());
        // you can also pass as a parameter any object like this: any(Book.class)

    }

    @Test
    public void webServiceIsUsedIfDataIsNotPresentInDatabase() {

        when(testDatabaseService.lookup("0140177396")).thenReturn(null);
        when(testWebService.lookup("0140177396")).thenReturn(new Book("0140177396", "abd","abc"));

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        // verify that in my class this method was called int (?) times using these parameters (params)
        // we don't care what the return values are, we are verifying how many times was this method called
        verify(testDatabaseService, times(1)).lookup("0140177396");

        // we don't care what string is used, we just want to know how many times the method was used
        verify(testWebService, times(1)).lookup("0140177396");
    }



    // ----- USING MOCKS (before setUp and tearDown) -----
//    @Test
//    public void testCanGetACorrectLocatorCode() {
//
//        ExternalISBNDataService testWebService = mock(ExternalISBNDataService.class);
//        when(testWebService.lookup(anyString())).thenReturn(new Book ("0140177396", "Of Mice And Men", "J. Steinbeck"));
//
//        ExternalISBNDataService testDatabaseService = mock(ExternalISBNDataService.class);
//        when(testDatabaseService.lookup(anyString())).thenReturn(null);
//
//        StockManager stockManager = new StockManager();
//        stockManager.setWebService(testWebService);
//        stockManager.setDatabaseService(testDatabaseService);
//
//        String isbn = "0140177396";
//        String locatorCode = stockManager.getLocatorCode(isbn);
//        assertEquals("7396J4", locatorCode);
//    }


    // ----- USING STUBS (before adding Mockito) -----
//    @Test
//    public void testCanGetACorrectLocatorCode() {
//
//        ExternalISBNDataService testWebService = new ExternalISBNDataService() {
//            @Override
//            public Book lookup(String isbn) {
//                return new Book(isbn, "Of Mice And Men", "J. Steinbeck");
//            }
//        };
//
//        ExternalISBNDataService testDatabaseService = new ExternalISBNDataService() {
//            @Override
//            public Book lookup(String isbn) {
//                return null;
//            }
//        };
//
//        StockManager stockManager = new StockManager();
//        stockManager.setWebService(testWebService);
//        stockManager.setDatabaseService(testDatabaseService);
//
//        String isbn = "0140177396";
//        String locatorCode = stockManager.getLocatorCode(isbn);
//        assertEquals("7396J4", locatorCode);
//    }

}
