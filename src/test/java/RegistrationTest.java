import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.CoreMatchers.equalTo;

public class RegistrationTest {

    //--Параметры пользователя для регистрации--
    //Служебный метод для генерации логина для тестового набора данных. В качестве параметра передается длинна генерируемой строки
    String userName = RandomStringUtils.randomAlphabetic(10);
    //Служебный метод для генерации части email (до знака @) пользователя для тестового набора данных. В качестве параметра передается длинна генерируемой строки
    String userEmail = RandomStringUtils.randomAlphabetic(8).toLowerCase(Locale.ROOT) + "@testmail.ru";
    //введите корректный пароль
    String userCorrectPassword = "123456";
    //введите некорректный пароль
    String userIncorrectPassword = "12345";


    //Служебный метод по удалению созданных пользователей после прохождения тестов
    @After
    public void deleteCreatedUserAfterTest() {
        DeleteUserServiceClass deleteUserServiceClass = new DeleteUserServiceClass();
        Response authResponse = deleteUserServiceClass.createUserAuthorizationAPIRequest(deleteUserServiceClass.createFullUserAuthorizationData(userEmail, userCorrectPassword));
        Boolean authStatus = authResponse.then().extract().path("success");
        if (authStatus == true) {
            String userAccessTokenFromResponse = authResponse.then().extract().path("accessToken").toString();
            Response deleteUserResponse = deleteUserServiceClass.deleteUserByAccessToken(userAccessTokenFromResponse);
            deleteUserResponse.then().assertThat().body("success", equalTo(true)).and().statusCode(202);
        }
    }

    //Проверка регистрации пользователя с не корректным паролем
    @Test
    @DisplayName("User registration with incorrect password")
    @Description("User registration with incorrect password test")
    public void newUserRegistrationTestWithIncorrectPassword() {
        MainPage mainPage = open(MainPage.mainPageAddress, MainPage.class);
        mainPage.clickHeaderLKButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterButton();
        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.fillRegFields(0, userName);
        registerPage.fillRegFields(1, userEmail);
        registerPage.fillRegFields(2, userIncorrectPassword);
        registerPage.clickOnRegButton();
        registerPage.incorrectPasswordMessageCheck();
    }

    //Проверка регистрации пользователя с корректным паролем
    @Test
    @DisplayName("User registration")
    @Description("New user registration test")
    public void newUserRegistrationTest() {
        MainPage mainPage = open(MainPage.mainPageAddress, MainPage.class);
        mainPage.clickHeaderLKButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterButton();
        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.fillRegFields(0, userName);
        registerPage.fillRegFields(1, userEmail);
        registerPage.fillRegFields(2, userCorrectPassword);
        registerPage.clickOnRegButton();
        loginPage.loginPageOpenCheck();
    }


}