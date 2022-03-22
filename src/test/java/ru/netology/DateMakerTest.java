package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DateMakerTest {
    @Test
    void shouldTestCardDeliveryReorder() {

        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue(DateMaker.chooseCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(DateMaker.chooseDate(5));
        $("[data-test-id='name'] input").setValue(DateMaker.chooseName());
        $("[data-test-id='phone'] input").setValue(DateMaker.choosePhone());
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Запланировать")).click();
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно запланирована на " + DateMaker.chooseDate(5)));

        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(DateMaker.chooseDate(7));
        $$("button").find(exactText("Запланировать")).click();

        $$("button").find(exactText("Перепланировать")).shouldBe(Condition.visible).click();
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно запланирована на " + DateMaker.chooseDate(7)));
    }
}
