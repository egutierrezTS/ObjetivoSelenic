package ddddd.page;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class CheckoutsteponePage {

    @FindBy(name = "firstName")
    private WebElement firstNameText;

    @FindBy(name = "lastName")
    private WebElement lastNameText;

    @FindBy(name = "postalCode")
    private WebElement postalCodeText;

    @FindBy(css = "input[value='Continue']")
    private WebElement continueSubmit;

    private WebDriver driver;

    private static final Duration DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = Duration.ofSeconds(15);

    public CheckoutsteponePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private WebElement waitFor(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
        wait.ignoring(StaleElementReferenceException.class);
        return wait.until(elementToBeClickable(element));
    }

    private WebElement scrollTo(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
        return element;
    }

    protected WebElement click(WebElement element) {
        WebElement webElement = scrollTo(waitFor(element));
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
        return wait.ignoring(ElementClickInterceptedException.class).until(webDriver -> {
            webElement.click();
            return webElement;
        });
    }

    public void setFirstNameText(String text) {
        waitFor(firstNameText).clear();
        firstNameText.sendKeys(text);
    }


    public void setLastNameText(String text) {
        waitFor(lastNameText).clear();
        lastNameText.sendKeys(text);
    }


    public void setPostalCodeText(String text) {
        waitFor(postalCodeText).clear();
        postalCodeText.sendKeys(text);
    }


    public void clickContinueSubmit() {
        click(continueSubmit);
    }


}
