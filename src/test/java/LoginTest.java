import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class LoginTest {

    //--Параметры пользователя для регистрации--
    //Служебный метод для генерации логина для тестового набора данных. В качестве параметра передается длинна генерируемой строки
    static String userName = RandomStringUtils.randomAlphabetic(10);
    //Служебный метод для генерации части email (до знака @) пользователя для тестового набора данных. В качестве параметра передается длинна генерируемой строки
    static String userEmail = RandomStringUtils.randomAlphabetic(8).toLowerCase(Locale.ROOT) + "@testmail.ru";
    //Введите корректный пароль
    static String userCorrectPassword = "123456";


    //Регистрации пользователя перед тестами
    @BeforeClass
    @DisplayName("User registration")
    @Description("New user registration")
    public static void newUserRegistration() {
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

    //Деавторизация пользователя после прохождения теста
    @After
    @DisplayName("Logout user after test")
    @Description("Logout user after test")
    public void userLogoutAfterEachTest() {
        MainPage mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        LoginPage loginPage = page(LoginPage.class);
        mainPage.mainPageOpenCheck();
        mainPage.clickHeaderLKButton();
        UserCabinet userCabinet = page(UserCabinet.class);
        userCabinet.lkPageOpenCheck();
        userCabinet.lkLogoutClick();
        loginPage.loginPageOpenCheck();
    }

    //Проверка входа по кнопке «Войти в аккаунт» на главной странице
    @Test
    @DisplayName("login from enter account button on main page")
    @Description("login from enter account button on main page test")
    public void mainPageAuthButtonTest() {
        MainPage mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        mainPage.clickBodyAuthButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillAuthForm(userEmail, userCorrectPassword);
        loginPage.clickAuthButton();
        mainPage.mainPageOpenCheck();
    }

    //Проверка входа по кнопке «Личный кабинет» на главной странице
    @Test
    @DisplayName("login from Lk button on main page header")
    @Description("login from Lk button on main page header test")
    public void mainPageLKAuthButtonTest() {
        MainPage mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        mainPage.clickHeaderLKButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillAuthForm(userEmail, userCorrectPassword);
        loginPage.clickAuthButton();
        mainPage.mainPageOpenCheck();
    }

    //Проверка входа через кнопку в форме регистрации
    @Test
    @DisplayName("login from registration form on registration page")
    @Description("login from registration form on registration page test")
    public void authFromRegPageTest() {
        MainPage mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        mainPage.clickBodyAuthButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegisterButton();
        RegisterPage registerPage = page(RegisterPage.class);
        registerPage.regPageOpenCheck();
        registerPage.clickOnAuthLink();
        loginPage.fillAuthForm(userEmail, userCorrectPassword);
        loginPage.clickAuthButton();
        mainPage.mainPageOpenCheck();
    }

    //Проверка входа через кнопку в форме восстановления пароля
    @Test
    @DisplayName("login from password restore page")
    @Description("login from password restore page test")
    public void authFromPasswordRestorePageTest() {
        MainPage mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        mainPage.clickBodyAuthButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRestorePasswordLink();
        ForgotPasswordPage forgotPasswordPage = page(ForgotPasswordPage.class);
        forgotPasswordPage.forgotPasswordPageOpenCheck();
        forgotPasswordPage.clickOnAuthLink();
        loginPage.loginPageOpenCheck();
        loginPage.fillAuthForm(userEmail, userCorrectPassword);
        loginPage.clickAuthButton();
        mainPage.mainPageOpenCheck();
    }


}