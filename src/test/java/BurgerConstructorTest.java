import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

public class BurgerConstructorTest {

    //Проверка кликов на разделы конструктора бургеров на главной странице
    @Test
    @DisplayName("Click on burger construction chapters on main page")
    @Description("Click on burger construction chapters on main page test")
    public void burgerConstructionFirstChapterClickTest() {
        MainPage mainPage = open(MainPage.mainPageAddress, MainPage.class);
        mainPage.clickOnConstructorChapterSelector(1);
        mainPage.dragAndDropSauceToBasket();
        mainPage.clickOnConstructorChapterSelector(2);
        mainPage.dragAndDropFillingToBasket();
        mainPage.clickOnConstructorChapterSelector(0);
        mainPage.dragAndDropBunToBasket();
    }


}