import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class LoginPage {

    //Адрес страницы входа на сайт
    public static String loginPageAddress = "https://stellarburgers.nomoreparties.site/login";

    //---Селекторы---
    //Ссылка "Зарегистрироваться" в теле страницы
    @FindBy(how = How.LINK_TEXT, using = "Зарегистрироваться")
    private SelenideElement registerLinkInBody;

    //Поле ввода "Email" области "Вход" в теле страницы
    @FindBy(how = How.XPATH, using = ".//div[@class='input__container']/div[@class='input pr-6 pl-6 input_type_text input_size_default']/input")
    private SelenideElement inputEmailField;

    //Поле ввода "Password" области "Вход" в теле страницы
    @FindBy(how = How.XPATH, using = ".//div[@class='input__container']/div[@class='input pr-6 pl-6 input_type_password input_size_default']/input")
    private SelenideElement inputPasswordField;

    //Ссылка "Войти" в теле страницы
    @FindBy(how = How.XPATH, using = ".//form[@Class='Auth_form__3qKeq mb-20']/button")
    private SelenideElement authButtonInBody;

    //Ссылка "Восстановить пароль" в теле страницы
    @FindBy(how = How.LINK_TEXT, using = "Восстановить пароль")
    private SelenideElement restorePasswordLinkInBody;


    //---Методы---
    //Метод кликает на кнопку Зарегистрироваться на странице логина
    @Step("Click on register button in login page")
    public void clickRegisterButton() {
        $(registerLinkInBody).shouldBe(Condition.visible).click();
    }

    //Метод заполняет поля "Email", "Пароль" области "Вход" в теле страницы
    @Step("Fill username and password fields on auth form")
    public void fillAuthForm(String username, String userPassword) {
        inputEmailField.setValue(username);
        inputPasswordField.setValue(userPassword);
    }

    //Метод кликает на кнопку Войти под полями ввода данных для авторизации
    @Step("Click on auth button below auth fields")
    public void clickAuthButton() {
        $(authButtonInBody).shouldBe(Condition.visible).click();
    }

    //Метод кликает на ссылку Восстановить пароль под полями ввода данных для авторизации
    @Step("Click on restore password button below auth fields")
    public void clickRestorePasswordLink() {
        $(restorePasswordLinkInBody).shouldBe(Condition.visible).click();
    }

    //Метод проверяет, что открыта страница входа на сайт
    @Step("Check what login page is open")
    public void loginPageOpenCheck() {
        webdriver().shouldHave(url(loginPageAddress));
    }


}