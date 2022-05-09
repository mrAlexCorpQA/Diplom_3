import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class UserCabinet {

    //Адрес личного кабинета пользователя
    public static String personalUserCabinet = "https://stellarburgers.nomoreparties.site/account/profile";

    //---Селекторы---
    //Ссылка "Конструктор" в хедере сайта
    @FindBy(how = How.XPATH, using = ".//a[@Class='AppHeader_header__link__3D_hX']")
    private SelenideElement headerConstructorLink;

    //Логотип "StellarBurgers" в хедере сайта
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement headerLogo;

    //Кнопка "Выход" (кнопка деавторизации) в теле сайта
    @FindBy(how = How.XPATH, using = ".//li[@Class='Account_listItem__35dAP']/button[@Class='Account_button__14Yp3 text text_type_main-medium text_color_inactive']")
    private SelenideElement logoutButton;


    //---Методы---
    //Метод проверяет, что открыта страница личного кабинета
    @Step("Check what lk page is open")
    public void lkPageOpenCheck() {
        webdriver().shouldHave(url(personalUserCabinet));
    }

    //Метод кликает на логотип "StellarBurgers" в хедере сайта
    @Step("Click on logo in header Lk")
    public void lkLogoClick() {
        $(headerLogo).shouldBe(Condition.visible).click();
    }

    //Метод кликает на ссылку "Конструктор" в хедере сайта
    @Step("Click on Constructor link in header Lk")
    public void lkConstructorLinkClick() {
        $(headerConstructorLink).click();
    }

    //Метод кликает на кнопку "Выход" в теле сайта
    @Step("Click logout button in body Lk page")
    public void lkLogoutClick() {
        $(logoutButton).shouldBe(Condition.visible).click();
    }


}