import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class ForgotPasswordPage {

    //Адрес страницы восстановления пароля
    private static final String FORGOT_PASSWORD_PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    //---Селекторы---
    //Ссылка "Войти" в теле страницы
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement authLinkInBody;

    //---Методы---
    //Метод кликает на ссылку "Войти" на странице регистрации пользователя
    @Step("Click on Auth link on forgot password page")
    public void clickOnAuthLink() {
        $(authLinkInBody).shouldBe(Condition.visible).click();
    }

    //Метод проверяет, что открыта страница восстановления пароля
    @Step("Check what forgot password page is open")
    public void forgotPasswordPageOpenCheck() {
        webdriver().shouldHave(url(FORGOT_PASSWORD_PAGE_URL));
    }

}