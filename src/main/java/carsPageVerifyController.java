import org.apache.commons.lang3.Range;
import org.openqa.selenium.WebElement;

public class carsPageVerifyController extends carsPageGetController {

    public carsPageVerifyController displayedCarsInPriceRange(int lowerRange, int upperRange) {
        System.out.println("Verifying all results are in price range " + lowerRange + " - " + upperRange);
        Range<Integer> range = Range.between(lowerRange, upperRange);

        waitForElementDisplayed(resultPrice);
        //todo: Improve this wait, can still continue before results are fully loaded
        int resultIndex = 0;
        for (WebElement carPriceSpan : driver.findElements(resultPrice)) {
            String result = "Found " + resultMakeModelTrim(resultIndex) + " with price of " + carPriceSpan.getText();
            System.out.println(result);
            if (!range.contains((Integer.parseInt(carPriceSpan.getText().replace(",", "")))))
                throw new IllegalArgumentException(
                        "Unexpected result = " + result);
            resultIndex++;
        }
        return this;
    }
}