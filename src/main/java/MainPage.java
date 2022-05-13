import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class MainPage {

    //Адрес главной страницы сайта
    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    //---Селекторы---
    //Кнопка "Личный кабинет" в хедере сайта
    @FindBy(how = How.LINK_TEXT, using = "Личный Кабинет")
    private SelenideElement headerPersonalCabinet;

    //Кнопка "Войти в аккаунт" на главной странице сайта
    @FindBy(how = How.XPATH, using = ".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']")
    private SelenideElement mainPageLoginBotton;

    //Раздел конструктора бургеров Булки (селектор) на главной странице сайта
    @FindBy(how = How.XPATH, using = ".//span[text() = 'Булки']")
    private SelenideElement bunChapterSelector;

    //Активный (выбранный) селектор конструктора бургеров Булки на главной странице сайта (используется в тесте по проверке перехода между табами)
    @FindBy(how = How.XPATH, using = ".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Булки']")
    private SelenideElement bunActiveChapterSelector;

    //Раздел конструктора бургеров Соусы (селектор) на главной странице сайта
    @FindBy(how = How.XPATH, using = ".//span[text() = 'Соусы']")
    private SelenideElement souseChapterSelector;

    //Активный (выбранный) селектор конструктора бургеров Соусы на главной странице сайта (используется в тесте по проверке перехода между табами)
    @FindBy(how = How.XPATH, using = ".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Соусы']")
    private SelenideElement souseActiveChapterSelector;

    //Раздел конструктора бургеров Начинки (селектор) на главной странице сайта
    @FindBy(how = How.XPATH, using = ".//span[text() = 'Начинки']")
    private SelenideElement fillingChapterSelector;

    //Активный (выбранный) селектор конструктора бургеров Соусы на главной странице сайта (используется в тесте по проверке перехода между табами)
    @FindBy(how = How.XPATH, using = ".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Начинки']")
    private SelenideElement fillingActiveChapterSelector;


    //---Методы---
    //Метод кликает на кнопку Личный кабинет в хедере страницы
    @Step("Click on lk button in main page header")
    public void clickHeaderLKButton() {
        $(headerPersonalCabinet).shouldBe(visible).click();
    }

    //Метод кликает на кнопку Войти в аккаунт в теле страницы
    @Step("Click on auth button in main page body page")
    public void clickBodyAuthButton() {
        $(mainPageLoginBotton).click();
    }

    //Метод проверяет, что открыта главная страница
    @Step("Check what main page is open")
    public void mainPageOpenCheck() {
        webdriver().shouldHave(url(MAIN_PAGE_URL));
    }

    //Метод проверяет переход на селектор Булки раздела конструктора бургеров
    @Step("Click on Bun selector of burger constructor chapter in main page")
    public void bunSelectorCheck() {
        $(souseChapterSelector).shouldBe(visible).click();
        $(bunChapterSelector).shouldBe(visible).click();
        $(bunActiveChapterSelector).shouldBe(exist, Duration.ofMillis(8000));
    }

    //Метод проверяет переход на селектор Соусы раздела конструктора бургеров
    @Step("Click on Souse selector of burger constructor chapter in main page")
    public void souseSelectorCheck() {
        $(souseChapterSelector).shouldBe(visible).click();
        $(souseActiveChapterSelector).shouldBe(exist, Duration.ofMillis(8000));
    }

    //Метод проверяет переход на селектор Начинки раздела конструктора бургеров
    @Step("Click on Filling selector of burger constructor chapter in main page")
    public void fillingSelectorCheck() {
        $(fillingChapterSelector).shouldBe(visible).click();
        $(fillingActiveChapterSelector).shouldBe(exist, Duration.ofMillis(8000));
    }


}