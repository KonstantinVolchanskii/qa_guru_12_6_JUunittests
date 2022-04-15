package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class WebTest {
    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = "1920x1080";
    }
    @BeforeEach
    void precondition(){
        open("https://film.ru");
    }
    @AfterEach
    void closeBrowser(){
        Selenide.closeWebDriver();
    }
    @ValueSource(strings = {"Мандалорец", "Mandalorian" })

    @ParameterizedTest(name = "поиск на сайте film.ru")

    @DisplayName("Проверка поиска на сайте по слову Мандалорец")
    void parameterizedTest(String testData) {


        $("#quick_search_input").setValue(testData).pressEnter();
        $$("#block-system-main")
                .find(Condition.text(testData))
                .shouldBe(visible);
//                () -> Assertions.assertEquals("Мандалорец" , "Мандалорец" , "Проверка  поиска по-русски "),
  //              () -> Assertions.assertEquals("Мандалорец" , "Mandalorian", "Проверка  поиска по-русско-английски")
    //    );
    }

}
//#__next > div.styles_root__nRLZC > div.styles_root__BJH2_.styles_headerContainer__f7XqC > header > div > div.styles_mainContainer__faOVn.styles_hasSidebar__rU_E2 > div.styles_searchFormContainerWide__3taA3.styles_searchFormContainer__GyAL5 > div > form > div > input