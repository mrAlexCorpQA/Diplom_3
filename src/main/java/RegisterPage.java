import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class RegisterPage {

    //Адрес страницы регистрации
    public static String registerPageAddress = "https://stellarburgers.nomoreparties.site/register";

    //---Селекторы---
    //Ссылка "Зарегистрироваться" в теле страницы
    @FindBy(how = How.XPATH, using = ".//button[@Class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']")
    private SelenideElement registerLinkInBody;

    //Ссылка "Войти" в теле страницы
    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement authLinkInBody;

    //Поля ввода "Имя", "email" (область "Регистрация")
    @FindBy(how = How.XPATH, using = ".//div[@class = 'input__container']")
    private ElementsCollection typeNameEmailRegData;

    //Активное поле "Имя", "email" (область "Регистрация"). Используется для ввода
    @FindBy(how = How.XPATH, using = ".//div[@class = 'input pr-6 pl-6 input_type_text input_size_default input_status_active']/input")
    private SelenideElement typeRegDataActive;

    //Сообщение об ошибке "Некорректный пароль"
    @FindBy(how = How.XPATH, using = ".//p[@Class='input__error text_type_main-default']")
    private SelenideElement incorrectPasswordErrorMessage;


    //---Методы---
    //Метод кликает на ссылку "Войти" на странице регистрации пользователя
    @Step("Click on Auth link on reg page")
    public void clickOnAuthLink() {
        $(authLinkInBody).shouldBe(Condition.visible).click();
    }

    //Метод проверяет, что открыта страница регистрации
    @Step("Check what reg page is open")
    public void regPageOpenCheck() {
        webdriver().shouldHave(url(registerPageAddress));
    }

    //Метод заполняет поля "Имя", "Email", "Пароль" (область "Регистрация")
    @Step("Fill username and email fields")
    public void fillRegFields(int numberOfField, String dataForFill) {
        typeNameEmailRegData.get(numberOfField).shouldBe(Condition.visible).click();
        typeRegDataActive.shouldBe(Condition.visible).setValue(dataForFill);
    }

    //Метод кликает на кнопку "Зарегистрироваться" на странице регистрации пользователя
    @Step("Click on Register button")
    public void clickOnRegButton() {
        $(registerLinkInBody).click();
    }

    //Метод проверяет, что сообщение об некорректном пароле отображается
    @Step("Click on Register button")
    public void incorrectPasswordMessageCheck() {
        $(incorrectPasswordErrorMessage).shouldBe(Condition.visible);
    }


}