import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserLogoutTest {
    //--Параметры пользователя для регистрации--
    //Служебный метод для генерации логина для тестового набора данных. В качестве параметра передается длинна генерируемой строки
    static String userName = RandomStringUtils.randomAlphabetic(10);
    //Служебный метод для генерации части email (до знака @) пользователя для тестового набора данных. В качестве параметра передается длинна генерируемой строки
    static String userEmail = RandomStringUtils.randomAlphabetic(8).toLowerCase(Locale.ROOT) + "@testmail.ru";
    //Введите корректный пароль
    static String userCorrectPassword = "123456";


    //Регистрация пользователя перед тестами
    @BeforeClass
    @DisplayName("User registration abefore tests")
    @Description("User registration before tests")
    public static void userRegistrationBeforeTests() {
        RegisterPage registerPage = open(RegisterPage.REGISTER_PAGE_URL, RegisterPage.class);
        registerPage.fillRegFieldName(userName);
        registerPage.fillRegFieldEmail(userEmail);
        registerPage.fillRegFieldPassword(userCorrectPassword);
        registerPage.clickOnRegButton();
    }

    //Служебный метод по удалению созданных пользователей после прохождения тестов
    @AfterClass
    public static void deleteCreatedUserAfterTest() {
        DeleteUserServiceClass deleteUserServiceClass = new DeleteUserServiceClass();
        Response authResponse = deleteUserServiceClass.createUserAuthorizationAPIRequest(deleteUserServiceClass.createFullUserAuthorizationData(userEmail, userCorrectPassword));
        Boolean authStatus = authResponse.then().extract().path("success");
        if (authStatus == true) {
            String userAccessTokenFromResponse = authResponse.then().extract().path("accessToken").toString();
            Response deleteUserResponse = deleteUserServiceClass.deleteUserByAccessToken(userAccessTokenFromResponse);
            deleteUserResponse.then().assertThat().body("success", equalTo(true)).and().statusCode(202);
        }
    }

    //Проверка деавторизации пользователя после клика на кнопку деавторизации «Выход» в личном кабинете пользователя
    @Test
    @DisplayName("Check logout after click on logout button in Lk body page")
    @Description("Check logout after click on logout button in Lk body page test")
    public void lkLogoutCheckTest() {
        MainPage mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        mainPage.clickBodyAuthButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillAuthForm(userEmail, userCorrectPassword);
        loginPage.clickAuthButton();
        mainPage.mainPageOpenCheck();
        mainPage.clickHeaderLKButton();
        UserCabinet userCabinet = page(UserCabinet.class);
        userCabinet.lkPageOpenCheck();
        userCabinet.lkLogoutClick();
        loginPage.loginPageOpenCheck();
    }


}