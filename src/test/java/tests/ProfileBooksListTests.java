package tests;

import api.BooksApi;
import models.lombok.AddBooksListModel;
import models.lombok.IsbnModel;
import models.lombok.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import java.util.Collections;
import java.util.List;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static tests.TestData.book;
import static tests.TestData.credentials;


public class ProfileBooksListTests extends TestBase {

    @Test
    @DisplayName("Удаляем книгу из профиля пользователя")
    void deleteBookFromProfileTest() {

        LoginResponseModel loginResponse = authorizationApi.login(credentials);

        BooksApi booksApi = new BooksApi();
        booksApi.deleteAllBooks(loginResponse);

        IsbnModel isbnModel = new IsbnModel(book.getIsbn());
        List<IsbnModel> isbnList = Collections.singletonList(isbnModel);
        AddBooksListModel booksList = new AddBooksListModel(loginResponse.getUserId(), isbnList);

        booksApi.addBook(loginResponse, booksList);

        step("Куки для авторизации в браузере");
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new org.openqa.selenium.Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new org.openqa.selenium.Cookie("token", loginResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));

        step("Открываем профиль и проверяем наличие книги");
        open("/profile");
        $("[id='see-book-" + book.getTitle() + "']").shouldBe(visible);

        booksApi.deleteBook(loginResponse, book.getIsbn());

        step("Открываем профиль и проверяем, что книга исчезла");
        open("/profile");
        $("[id='see-book-" + book.getTitle() + "']").shouldNotBe(visible);
    }
}
