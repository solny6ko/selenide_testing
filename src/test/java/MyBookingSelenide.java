
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.Arrays;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class MyBookingSelenide {
    public static void main(String[] args) {
        open("https://booking.com");
        $x("//button[@id='onetrust-accept-btn-handler']").shouldBe(visible).click();

        $x("//input[@name='ss']").sendKeys("Paris");
        $x("//div[text()='Ile de France, France']").click();
        int checkInDay = LocalDate.now().plusDays(3).getDayOfMonth();
        int checkOutDay = LocalDate.now().plusDays(10).getDayOfMonth();
        String pathToDay = "//div[@data-testid='searchbox-datepicker-calendar']/div/div[1]/table/tbody//span[text()='%s']";
        $x(String.format(pathToDay, checkInDay)).click();
        $x(String.format(pathToDay, checkOutDay)).click();
        $x("//span[@data-testid='occupancy-config-icon']").click();
        WebElement adults = $x("//label[@for='group_adults']/../following-sibling::div/button[2]");
        adults.click();
        adults.click();
        WebElement rooms = $x("//label[@for='no_rooms']/../following-sibling::div/button[2]");
        rooms.click();

        $x("//button[@type='submit']").click();
        $x("//button[@aria-label='Dismiss sign-in info.']").shouldBe(visible).click();


        $x("//div[@data-filters-item='review_score:review_score=60']//span[2]").click();
        $x("//span[text()='Pleasant: 6+']").shouldBe(visible);
        $x("//button[@data-testid='sorters-dropdown-trigger']").click();
        $x("//button[@data-id='class_asc']").click();


        String score = $x("//div[@data-testid='property-card'][1]//div[@data-testid='review-score']/div[1]/div").getText();
        System.out.println(score + " Div value");

        String[] arrOfStr = score.split(" ");
        System.out.println(Arrays.toString(arrOfStr) + " Array from div value");
        String scoreString = arrOfStr[1];
        System.out.println(scoreString + " Score value in string");
        String[] arrayScoreString = scoreString.split("\\.");
        System.out.println(Arrays.toString(arrayScoreString) + " Array from Score value");
        String onlyScoreValue = arrayScoreString[0];
        System.out.println(onlyScoreValue);

        assertEquals("Score of the first sorted hotel is wrong", "6", onlyScoreValue);

    }
}
