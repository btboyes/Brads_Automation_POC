import org.junit.After;
import org.junit.Test;
import org.testng.annotations.BeforeClass;

public class searchCars extends basePage {

    @BeforeClass
    public static void setupClass() {

    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    //Log in first to avoid mailing list modal intercepting clicks
    public void priceRangeSearchTest() {
        driver.navigate().to("https://www.carvana.com/cars");
        carsPage.act()
                .logInAs("btboyes@gmail.com", "1!Password")
                .makeNoteOfResultCount()
                .openSearchFilter("Payment & Price")
                .chooseFinanceOrPriceFilter("Price")
                .setLowerPriceRange(10000)
                .setUpperPriceRange(20000)
                .closeSearchFilter("Payment & Price")
                .waitForResultCountToChange();
        carsPage.verify()
                .displayedCarsInPriceRange(10000, 20000);
    }
}
