import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

public class BurgerConstructorTest {


    //Проверка перехода на раздел конструктора бургеров Булки на главной странице
    @Test
    @DisplayName("Check Bun burger construction chapters on main page")
    @Description("Check Bun burger construction chapters on main page test")
    public void burgerConstructionBunChapterTest() {
        MainPage mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        mainPage.bunSelectorCheck();
    }

    //Проверка перехода на раздел конструктора бургеров Соусы на главной странице
    @Test
    @DisplayName("Check Souse burger construction chapters on main page")
    @Description("Check Souse burger construction chapters on main page test")
    public void burgerConstructionSouseChapterTest() {
        MainPage mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        mainPage.souseSelectorCheck();
    }

    //Проверка перехода на раздел конструктора бургеров Начинки на главной странице
    @Test
    @DisplayName("Check Filling burger construction chapters on main page")
    @Description("Check Filling burger construction chapters on main page test")
    public void burgerConstructionFillingChapterTest() {
        MainPage mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        mainPage.fillingSelectorCheck();
    }


}