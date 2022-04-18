package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class WebTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void precondition() {
        open("https://parkovkaplatov.com/#bl-3");
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }

    @ValueSource(strings = {"Федор", "Бобров"})

    @ParameterizedTest(name = "fillform on parkovkaplatov.com {0}")

    @DisplayName("Заполнение формы бронирования")
    void parameterizedTest(String testData) {

        //заполнить поля
        $(byName("name_user")).setValue(testData);
        $(byName("tel_user")).setValue("88009001122");
        $(byName("datazaezda")).setValue("11122022");
        $(byName("dataviezda")).setValue("13122022");
        $(byName("vremPrib")).setValue("23");
        $(byName("vremUb")).setValue("11");
        $(byName("markamash")).setValue("Lexus");
        $(byName("nomermash")).setValue("Y420PA161RUS");
        //кликнуть Submit
        $("input[type=submit").click();
        //проверить значение в поле
        $("#spsModal").shouldHave(text("ВАША ЗАЯВКА ОТПРАВЛЕНА")).click();

    }

    @CsvSource(value = {
            "Fedor| 89514855969|14.02.2023| 19.02.2023| 22:15| 17:00| Lada| P142XT777RUS",
            "Bobrov| 89516933558| 17.05.2022| 20.05.2022| 13:00| 16:30| Mazda| M324PK171RUS"
    },
            delimiter = '|')

    @DisplayName("cvs")
    @ParameterizedTest(name = "fillform on parkovkaplatov.com {0}")
    void parameterizedCvTest(String testData, String telData, String dateInt, String dateOut, String timeInt, String timeOut, String carMod, String carNumb) {

//заполнить поля
        $(byName("name_user")).setValue(testData);
        $(byName("tel_user")).setValue(telData);
        $(byName("datazaezda")).setValue(dateInt);
        $(byName("dataviezda")).setValue(dateOut);
        $(byName("vremPrib")).setValue(timeInt);
        $(byName("vremUb")).setValue(timeOut);
        $(byName("markamash")).setValue(carMod);
        $(byName("nomermash")).setValue(carNumb);
        //кликнуть Submit
        $("input[type=submit").click();
        //проверить значение в поле
        $("#spsModal").shouldHave(text("ВАША ЗАЯВКА ОТПРАВЛЕНА")).click();
        
    }

        static Stream<Arguments> validationPhoneTest(){
            return Stream.of(
                    Arguments.of("89516931221"),
                    Arguments.of("88009003322")

            );
        }
        @MethodSource(value = "validationPhoneTest")
        @ParameterizedTest(name = "Проверка валидации телефона")
        void validationPhoneTest (String loginNum){
            $(byName("tel_user")).setValue(loginNum);
            $("input[type=submit").click();

        }
}
