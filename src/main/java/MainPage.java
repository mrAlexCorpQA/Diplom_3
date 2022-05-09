import com.codeborne.selenide.ElementsCollection;
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
    public static String mainPageAddress = "https://stellarburgers.nomoreparties.site/";

    //---Селекторы---
    //Кнопка "Личный кабинет" в хедере сайта
    @FindBy(how = How.LINK_TEXT, using = "Личный Кабинет")
    private SelenideElement headerPersonalCabinet;

    //Кнопка "Войти в аккаунт" на главной странице сайта
    @FindBy(how = How.XPATH, using = ".//div[@class='BurgerConstructor_basket__container__2fUl3 mt-10']/button")
    private SelenideElement mainPageLoginBotton;

    //Коллекция разделов конструктора бургеров (селектор Булки, Соусы, Начинки) на главной странице сайта
    @FindBy(how = How.XPATH, using = ".//div[@Style='display: flex;']/div")
    private ElementsCollection burgerConstructorSelectorChapters;

    //Флюоресцентная булка R2-D3. Первый элемент раздела Булки используется для проверки конструктора
    @FindBy(how = How.XPATH, using = ".//img[@alt='Флюоресцентная булка R2-D3']")
    private SelenideElement ingredientBunR2D3;

    //Соус Spicy-X. Первый элемент раздела Соусы используется для проверки конструктора
    @FindBy(how = How.XPATH, using = ".//img[@alt='Соус Spicy-X']")
    private SelenideElement ingredientSauceSpicyX;

    //Мясо бессмертных моллюсков Protostomia. Первый элемент раздела Начинки используется для проверки конструктора
    @FindBy(how = How.XPATH, using = ".//img[@alt='Мясо бессмертных моллюсков Protostomia']")
    private SelenideElement ingredientMeatProtostomia;

    //Корзина для сборки бургера. Используется для проверки конструктора
    @FindBy(how = How.XPATH, using = ".//ul[@class='BurgerConstructor_basket__list__l9dp_']")
    private SelenideElement burgerConstructorBasket;


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
        webdriver().shouldHave(url(mainPageAddress));
    }

    //Метод кликает на выбранный селектор раздела конструктора бургеров
    @Step("Click on selector of burger constructor chapter in main page")
    public void clickOnConstructorChapterSelector(int chapterNumber) {
        $$(burgerConstructorSelectorChapters).get(chapterNumber).click();
        $$(burgerConstructorSelectorChapters).get(chapterNumber).shouldHave(attribute("class", "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect"), Duration.ofMillis(8000));
    }

    //Метод перетаскивает выбранную булочку в корзину
    @Step("Drag and Drop ingredient Bun to basket")
    public void dragAndDropBunToBasket() {
        ingredientBunR2D3.shouldBe(enabled).dragAndDropTo(burgerConstructorBasket);
    }

    //Метод перетаскивает выбранный соус в корзину
    @Step("Drag and Drop ingredient Sauce to basket")
    public void dragAndDropSauceToBasket() {
        ingredientSauceSpicyX.shouldBe(enabled).dragAndDropTo(burgerConstructorBasket);
    }

    //Метод перетаскивает выбранную начинку в корзину
    @Step("Drag and Drop ingredient Filling to basket")
    public void dragAndDropFillingToBasket() {
        ingredientMeatProtostomia.dragAndDropTo(burgerConstructorBasket);
    }


}