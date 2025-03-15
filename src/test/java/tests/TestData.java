package tests;

import models.lombok.BookModel;
import models.lombok.CredentialsModel;

public class TestData {

    private static String login = "Vtalik",
            password = "Vitalik_123!";

    public static CredentialsModel credentials = new CredentialsModel(login, password);

    private static String isbn = "9781449365035",
            title = "Speaking JavaScript";

    public static final BookModel book = new BookModel(isbn, title);
}
