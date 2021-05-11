import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class carsPageGetController extends basePage {


    //region Sign In
    By signInLink = By.xpath("//a[contains(@class,'SignInLink__StyledMenuButton')]");
    By emailInput = By.id("usernameField");
    By passwordInput = By.id("passwordField");
    By signInButton = By.xpath("//button[@type='submit']");
    By signedInAccountDiv = By.xpath("//div[contains(@class,'Account__SignInWrapper')]");

    //region Payment & Price
    //A displayed text is not my preferred locator, since it is a culture-dependant property.
    //If there are plans to add language options, I would want to revisit this to find a better option
    By paymentAndPriceFilterButton = By.xpath("//span[text()='PAYMENT & PRICE']/..");
    By paymentAndPriceSwitch = By.xpath("//div[contains(@class,'Budgetstyles__StyledSwitch')]");
    By priceRangeInputs = By.xpath("//div[contains(@class,'price-range')]//input[@type='text']");
    //endregion

    //region Results
    By resultsCountText = By.xpath("//div[contains(@class,'ResultsCount')]");
    //resultTile and resultPrice locators are inconsistent. Almost as if I'm hitting different nodes with different code?
    By resultTile = By.xpath("//div[contains(@class,'ShowroomResultTile')]");
//    By resultTile = By.className("result-tile");
    By resultPrice = By.xpath("//h4[contains(@class,'vehicle-price')]//span[2]");
//    By resultPrice = By.className("price");
    By resultMakeModelTrim = By.xpath("//div[@class='year-make']/..");
    //endregion

    //Gets the expandable selectlist element
    WebElement searchFilter(String filterText) {
        switch (filterText.toLowerCase().replace(" ", "")) {
            case "payment&price":
            default:
                return webElement(paymentAndPriceFilterButton);
        }
    }

    //Checking ClassName of this object since it changes when the filter expands
    boolean searchFilterIsExpanded(String filterText) {
        String classText = searchFilter(filterText).getAttribute("class");
        if (classText.contains("bGsPri"))
            return false;
        if (classText.contains("iSOpXQ"))
            return true;
        throw new WebDriverException("Unable to assert state of " + filterText + " search filter");
    }

    //Gets the result count text from the top of the results grid
    String resultCount() {
        String count = webElement(resultsCountText).getText().replace(",", "").split(": ")[1];
        updateSessionData("searchResultCount", String.valueOf(count));
        return count;
    }

    //Gets the parent element of the make/model/trim elements to read text of all at once
    String resultMakeModelTrim(int index) {
        return webElement(resultMakeModelTrim, index).getText().replaceAll("\\n", " ");
    }
}


