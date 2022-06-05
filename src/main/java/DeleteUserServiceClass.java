import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteUserServiceClass {


    //URL адрес endpoint для авторизации пользователя
    private static final String AUTHORIZATION_ENDPOINT_URL = "https://stellarburgers.nomoreparties.site/api/auth/login";

    //URL адрес endpoint для удаления пользователя
    private static final String MAIN_USER_ENDPOINT_URL = "https://stellarburgers.nomoreparties.site/api/auth/user";

    //Служебный метод для создания полного набора данных для авторизации пользователя
    @Step("Create full user test authorization data")
    public String createFullUserAuthorizationData(String userEmailAdress, String userPassword) {
        String authorizationDataFull = "{\"email\":\"" + userEmailAdress + "\","
                + "\"password\":\"" + userPassword + "\"}";
        return authorizationDataFull;
    }

    //Служебный метод для отправки запроса на авторизацию пользователя.
    @Step("Create user authorization sending")
    public Response createUserAuthorizationAPIRequest(String regDataVariant) {
        Response authorizationUserUserResponse = given()
                .header("Content-type", "application/json")
                .and()
                .body(regDataVariant)
                .when()
                .post(AUTHORIZATION_ENDPOINT_URL);
        return authorizationUserUserResponse;
    }

    //Служебный метод на удаление пользователя
    @Step("Delete user by token (/api/auth/user)")
    public Response deleteUserByAccessToken(String userToken) {
        Response userDeleteResponse = given()
                .header("Authorization", userToken)
                .and()
                .delete(MAIN_USER_ENDPOINT_URL);
        return userDeleteResponse;
    }

}