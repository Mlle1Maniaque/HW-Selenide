import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    public static String setLocalDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy",
                new Locale("ru")));
    }

    @BeforeEach
    void setUpTest() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSetValidCity(){
        String date = setLocalDate(3);
        $("[data-test-id=city] .input__control").setValue("Санкт-Петербург");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(date);
        $("[data-test-id=name] .input__control").setValue("Смирнова Анна");
        $("[data-test-id=phone] .input__control").setValue("+79370006989");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=notification]").shouldHave(text("Успешно!"),
                Duration.ofSeconds(15)).shouldBe(visible);
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + date),
                Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldSetInvalidCity(){
        String date = setLocalDate(3);
        $("[data-test-id=city] .input__control").setValue("Кондопога");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(date);
        $("[data-test-id=name] .input__control").setValue("Смирнова Анна");
        $("[data-test-id=phone] .input__control").setValue("+79370006989");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='city'].input_invalid").shouldBe(visible, Duration.ofSeconds(5))
                .should(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldSetCityEnglish(){
        String date = setLocalDate(3);
        $("[data-test-id=city] .input__control").setValue("Saint-Petersburg");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(date);
        $("[data-test-id=name] .input__control").setValue("Смирнова Анна");
        $("[data-test-id=phone] .input__control").setValue("+79370006989");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='city'].input_invalid").shouldBe(visible, Duration.ofSeconds(5))
                .should(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldSetValidData() {
        String date = setLocalDate(5);
        $("[data-test-id=city] .input__control").setValue("Москва");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(date);
        $("[data-test-id=name] .input__control").setValue("Смирнова Анна");
        $("[data-test-id=phone] .input__control").setValue("+79370006989");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=notification]").shouldHave(text("Успешно!"),
                Duration.ofSeconds(15)).shouldBe(visible);
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + date),
                Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldSetValidDateYear() {
        String date = setLocalDate(365);
        $("[data-test-id=city] .input__control").setValue("Петрозаводск");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(date);
        $("[data-test-id=name] .input__control").setValue("Смирнова Анна");
        $("[data-test-id=phone] .input__control").setValue("+79370006989");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=notification]").shouldHave(text("Успешно!"),
                Duration.ofSeconds(15)).shouldBe(visible);
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + date),
                Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldSetInvalidDate() {
        String date = setLocalDate(2);
        $("[data-test-id=city] .input__control").setValue("Санкт-Петербург");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(date);
        $("[data-test-id=name] .input__control").setValue("Смирнова Анна");
        $("[data-test-id=phone] .input__control").setValue("+79370006989");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(withText("невозможен")).shouldBe(visible, Duration.ofSeconds(5));
        $x("//*[@data-test-id=\"notification\"]").shouldNot(visible, Duration.ofSeconds(10));
    }

    @Test
    void shouldSetInvalidDateZero() {
        String date = setLocalDate(0);
        $("[data-test-id=city] .input__control").setValue("Саратов");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(date);
        $("[data-test-id=name] .input__control").setValue("Смирнова Анна");
        $("[data-test-id=phone] .input__control").setValue("+79370006989");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(withText("невозможен")).shouldBe(visible, Duration.ofSeconds(5));
        $x("//*[@data-test-id=\"notification\"]").shouldNot(visible, Duration.ofSeconds(10));
    }

    @Test
    void shouldSetValidName() {
        String date = setLocalDate(3);
        $("[data-test-id=city] .input__control").setValue("Москва");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(date);
        $("[data-test-id=name] .input__control").setValue("Иванова-Смирнова Анна-Мария");
        $("[data-test-id=phone] .input__control").setValue("+79370006989");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=notification]").shouldHave(text("Успешно!"),
                Duration.ofSeconds(15)).shouldBe(visible);
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + date),
                Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldSetInvalidName() {
        String date = setLocalDate(3);
        $("[data-test-id=city] .input__control").setValue("Санкт-Петербург");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(date);
        $("[data-test-id=name] .input__control").setValue("Smirnova Anna");
        $("[data-test-id=phone] .input__control").setValue("+79370006989");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(withText("неверно")).shouldBe(visible, Duration.ofSeconds(5));
        $x("//*[@data-test-id=\"notification\"]").shouldNot(visible, Duration.ofSeconds(10));
    }

    @Test
    void shouldSetValidPhone() {
        String date = setLocalDate(6);
        $("[data-test-id=city] .input__control").setValue("Тверь");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(date);
        $("[data-test-id=name] .input__control").setValue("Петрова Екатерина");
        $("[data-test-id=phone] .input__control").setValue("+79522680000");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=notification]").shouldHave(text("Успешно!"),
                Duration.ofSeconds(15)).shouldBe(visible);
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на " + date),
                Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldSetInvalidPhone() {
        String date = setLocalDate(3);
        $("[data-test-id=city] .input__control").setValue("Санкт-Петербург");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(date);
        $("[data-test-id=name] .input__control").setValue("Смирнова Анна");
        $("[data-test-id=phone] .input__control").setValue("+793700069899");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(withText("неверно")).shouldBe(visible, Duration.ofSeconds(5));
        $x("//*[@data-test-id=\"notification\"]").shouldNot(visible, Duration.ofSeconds(10));
    }

    @Test
    void shouldSetInvalidPhone1() {
        String date = setLocalDate(3);
        $("[data-test-id=city] .input__control").setValue("Москва");
        $("[data-test-id=date] .input__control").doubleClick().sendKeys(date);
        $("[data-test-id=name] .input__control").setValue("Смирнова Анна");
        $("[data-test-id=phone] .input__control").setValue("79370006989");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(withText("неверно")).shouldBe(visible, Duration.ofSeconds(5));
        $x("//*[@data-test-id=\"notification\"]").shouldNot(visible, Duration.ofSeconds(10));
    }
}
