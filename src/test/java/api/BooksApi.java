package api;

import io.qameta.allure.Step;
import models.lombok.AddBooksListModel;
import models.lombok.DeleteBookListModel;
import models.lombok.LoginResponseModel;
import static io.restassured.RestAssured.given;
import static specs.TestSpec.*;

public class BooksApi {

    @Step("Удаляем все книги из профиля пользователя")

    public void deleteAllBooks(LoginResponseModel loginResponse) {

        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(responseCod204Spec);
    }

    @Step("Добавляем одну книгу в профиль пользователя")

    public void addBook(LoginResponseModel loginResponse, AddBooksListModel booksList) {
        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(booksList)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(responseCod201Spec);
    }

    @Step("Удаляем одну книгу из профиля пользователя")

    public void deleteBook(LoginResponseModel loginResponse, String isbn) {
        DeleteBookListModel deleteRequest = new DeleteBookListModel();
        deleteRequest.setIsbn(isbn);
        deleteRequest.setUserId(loginResponse.getUserId());

        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(deleteRequest)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(responseCod204Spec);
    }
}
