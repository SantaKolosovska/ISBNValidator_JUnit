public class StockManager {

    //Locator code will be created from last 4 digits of ISBN code + initial of author + number of words in title

    private ExternalISBNDataService webService;
    private ExternalISBNDataService databaseService;

    public String getLocatorCode(String isbn) {
        Book book = databaseService.lookup(isbn);       // if book wasn't retrieved from db service
        if (book == null) book = webService.lookup(isbn);      // use web service
        StringBuilder locator = new StringBuilder();
        locator.append(isbn.substring(isbn.length()-4));
        locator.append(book.getAuthor().substring(0, 1));
        locator.append(book.getTitle().split(" ").length);
        return locator.toString();
    }

    public void setWebService(ExternalISBNDataService webService) {
        this.webService = webService;
    }

    public void setDatabaseService(ExternalISBNDataService databaseService) {
        this.databaseService = databaseService;
    }
}
