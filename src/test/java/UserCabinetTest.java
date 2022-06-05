import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserCabinetTest {

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


    //Проверка входа в личный кабинет по кнопке «Личный кабинет» в хедере сайта после авторизации пользователя
    @Test
    @DisplayName("Check redirect to Lk page after authorization")
    @Description("Check redirect to Lk page after authorization test")
    public void lkRedirectAfterAuthTest() {
        MainPage mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        mainPage.clickBodyAuthButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillAuthForm(userEmail, userCorrectPassword);
        loginPage.clickAuthButton();
        mainPage.mainPageOpenCheck();
        mainPage.clickHeaderLKButton();
        UserCabinet userCabinet = page(UserCabinet.class);
        userCabinet.lkPageOpenCheck();
    }

    //Проверка перехода на главную страницу по клику на логотип в личном кабинете после авторизации пользователя
    @Test
    @DisplayName("Check redirect to main page after click on logo in header")
    @Description("Check redirect to main page after click on logo in header test")
    public void lkLogoCheckRedirectTest() {
        MainPage mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        mainPage.clickBodyAuthButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillAuthForm(userEmail, userCorrectPassword);
        loginPage.clickAuthButton();
        mainPage.mainPageOpenCheck();
        mainPage.clickHeaderLKButton();
        UserCabinet userCabinet = page(UserCabinet.class);
        userCabinet.lkPageOpenCheck();
        userCabinet.lkLogoClick();
        mainPage.mainPageOpenCheck();
    }

    //Проверка перехода на главную страницу по клику на ссылку «Конструктор» в личном кабинете после авторизации пользователя
    @Test
    @DisplayName("Check redirect to main page after click on Constructor link in header")
    @Description("Check redirect to main page after click on Constructor link in header test")
    public void lkConstructorLinkCheckRedirectTest() {
        MainPage mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        mainPage.clickBodyAuthButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.fillAuthForm(userEmail, userCorrectPassword);
        loginPage.clickAuthButton();
        mainPage.mainPageOpenCheck();
        mainPage.clickHeaderLKButton();
        UserCabinet userCabinet = page(UserCabinet.class);
        userCabinet.lkPageOpenCheck();
        userCabinet.lkConstructorLinkClick();
        mainPage.mainPageOpenCheck();
    }


}