import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class carsPageActController extends carsPageGetController {

    public carsPageActController waitForResultCountToChange() {
        int tries = 0;
        while (tries < 5) {
            waitForElementDisplayed(resultsCountText);
            if (resultCount().equals(getSessionData("searchResultCount")))
                break;
            else tries++;
        }
        return this;
    }

    //Seeing this click not register on occasion, so adding try loop
    public carsPageActController openSearchFilter(String filterText) {
        openOrCloseSearchFilter(filterText, true);
        return this;
    }

    public carsPageActController closeSearchFilter(String filterText) {
        openOrCloseSearchFilter(filterText, false);
        return this;
    }

    private carsPageActController openOrCloseSearchFilter(String filterText, boolean shouldBeOpen) {
        waitForElementDisplayed(resultPrice);
        int tries = 0;
        while (!searchFilterIsExpanded(filterText) == shouldBeOpen
                && tries < 5) {
            searchFilter(filterText).click();
            tries++;
        }
        return this;
    }

    //These filters are on a toggle, so clicking the single element will always change the option
    public carsPageActController chooseFinanceOrPriceFilter(String financeOrPrice) {
        waitForElementClickable(paymentAndPriceSwitch);
        //If you want Finance, the aria-checked property should be false. Toggled by clicking the element
        if (
                financeOrPrice.equalsIgnoreCase("finance")
                        == Boolean.parseBoolean(webElement(paymentAndPriceSwitch).getAttribute("aria-checked"))
        )
            webElement(paymentAndPriceSwitch).click();
        return this;
    }

    public carsPageActController setLowerPriceRange(int value) {
        waitForElementDisplayed(priceRangeInputs);
        typeText(webElement(priceRangeInputs, 0), String.valueOf(value));
        waitForElementDisplayed(resultTile);
        return this;
    }

    public carsPageActController setUpperPriceRange(int value) {
        waitForElementDisplayed(priceRangeInputs);
        typeText(webElement(priceRangeInputs, 1), String.valueOf(value));
        waitForElementDisplayed(resultTile);
        return this;
    }

    public carsPageActController makeNoteOfResultCount() {
        resultCount();
        return this;
    }

    public carsPageActController logInAs(String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, 20);

        waitForElementClickable(signInLink);
        webElement(signInLink).click();
        waitForElementDisplayed(emailInput);
        typeText(webElement(emailInput), email);
        typeText(webElement(passwordInput), password);
        waitForElementClickable(signInButton);
        webElement(signInButton).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(emailInput)));
        waitForElementDisplayed(signedInAccountDiv);
        waitForElementDisplayed(resultTile);
        return this;
    }
}
