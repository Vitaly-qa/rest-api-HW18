package api;

import io.qameta.allure.Step;
import models.lombok.CredentialsModel;
import models.lombok.LoginResponseModel;
import static io.restassured.RestAssured.given;
import static specs.TestSpec.requestSpec;
import static specs.TestSpec.responseCod200Spec;


public class AuthorizationApi {

    @Step("Авторизация пользователя")
    public LoginResponseModel login(CredentialsModel credentials) {
        return given(requestSpec)
                .body(credentials)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(responseCod200Spec)
                .extract().as(LoginResponseModel.class);
    }


}
