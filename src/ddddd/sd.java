package ddddd;


import ddddd.page.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class sd {

    private static final String SAUCEDEMO_BASE_URL = "https://www.saucedemo.com";

    private WebDriver driver;

    @BeforeEach
    public void beforeTest() {
        ChromeOptions opts = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.managed_default_content_settings.geolocation", 2);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        opts.setExperimentalOption("prefs", prefs);
        opts.addArguments("--start-maximized");
        opts.addArguments("--incognito");
        opts.addArguments("--enable-strict-powerful-feature-restrictions");
        driver = new ChromeDriver(opts);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    public void testPruebaSauceDemo() throws Throwable {
        driver.get(System.getProperty("SAUCEDEMO_BASE_URL", SAUCEDEMO_BASE_URL) + "/");


        SwagLabsPage swagLabsPage = new SwagLabsPage(driver);
        swagLabsPage.setUserNameText("standard_user");
        swagLabsPage.setPassword("secret_sauce");
        swagLabsPage.clickLoginSubmit();


        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.clickAddToCartSauceLabsBackpackSubmit();
        inventoryPage.clickAddToCartSauceLabsBoltTShirtSubmit();
        inventoryPage.clickAddToCartSauceLabsOnesieSubmit();
        inventoryPage.clickAddToCartTestAllthethingsTShirtRedSubmit();
        inventoryPage.clickAddToCartSauceLabsFleeceJacketSubmit();
        inventoryPage.clickAddToCartSubmit();
        inventoryPage.clickLink();


        CartPage cartPage = new CartPage(driver);
        cartPage.clickContinueShoppingCheckout();
        cartPage.clickCheckoutSubmit();


        CheckoutsteponePage checkoutsteponePage = new CheckoutsteponePage(driver);
        checkoutsteponePage.setFirstNameText("carlitos");
        checkoutsteponePage.setLastNameText("lechuga");
        checkoutsteponePage.setPostalCodeText("999999");
        checkoutsteponePage.clickContinueSubmit();


        CheckoutsteptwoPage checkoutsteptwoPage = new CheckoutsteptwoPage(driver);
        checkoutsteptwoPage.clickFinishSubmit();


        CheckoutcompletePage checkoutcompletePage = new CheckoutcompletePage(driver);
        assertEquals("Thank you for your order!", checkoutcompletePage.getCompleteHeaderFieldText());
        checkoutcompletePage.clickBackHomeSubmit();
    }
}
