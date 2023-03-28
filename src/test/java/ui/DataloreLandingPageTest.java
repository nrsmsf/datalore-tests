package ui;

import com.github.javafaker.Faker;
import com.jetbrains.DataloreLandingPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@DisplayName("UI Tests for Datalore landing page")
public class DataloreLandingPageTest {
    Faker faker = new Faker();

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @BeforeEach
    public void setUp() {
        driver.get("https://k8s.stable.on-premise.datalore.io/");
    }

    @AfterEach
    public void turnOff() {
        driver.quit();
    }


    @Test
    @DisplayName("Check if all elements displayed correctly")
    public void checkAllElementsDisplayedTest() {
        var dataloreLandingPage = new DataloreLandingPage(driver);
        dataloreLandingPage.checkAllElementsDisplayed();
    }


    @Test
    @DisplayName("Check password show button works correctly")
    public void checkPasswordShowButtonTest() {
        var dataloreLandingPage = new DataloreLandingPage(driver);
        dataloreLandingPage.inputPassword(faker.internet().password());
        dataloreLandingPage.checkPasswordInputType("password");
        dataloreLandingPage.clickOnShowPasswordButton();
        dataloreLandingPage.checkPasswordInputType("text");
    }

    @Test
    @DisplayName("Check create an account button works correctly")
    public void checkCreateAccountButtonTest() {
        var dataloreLandingPage = new DataloreLandingPage(driver);
        dataloreLandingPage.checkLoginHeaderText("Log in");
        dataloreLandingPage.clickOnCreateAnAccountButton();
        dataloreLandingPage.checkLoginHeaderText("Sign up");
        dataloreLandingPage.checkLogInHereButtonDisplayed();
        dataloreLandingPage.checkCreateAccountButtonDisplayed();
    }

    @Test
    @DisplayName("Check that email address is correct")
    public void checkEmailAddressTest() {
        var dataloreLandingPage = new DataloreLandingPage(driver);
        dataloreLandingPage.checkSupportEmailAddress();
    }

    @Test
    @DisplayName("Check that documentation opens correctly")
    public void checkDocumentationButtonTest() {
        var dataloreLandingPage = new DataloreLandingPage(driver);
        dataloreLandingPage.clickOnDocumentationButton();
        dataloreLandingPage.switchToOpenedTab(driver);
        dataloreLandingPage.checkDocumentationUrl(driver);
        dataloreLandingPage.checkDocumentationTabTitle(driver);
    }

    @Test
    @DisplayName("Check that community forum opens correctly")
    public void checkForumButtonTest() {
        var dataloreLandingPage = new DataloreLandingPage(driver);
        dataloreLandingPage.clickOnCommunityForumButton();
        dataloreLandingPage.switchToOpenedTab(driver);
        dataloreLandingPage.checkForumUrl(driver);
        dataloreLandingPage.checkForumTabTitle(driver);
    }

    @Test
    @DisplayName("Check that community forum opens correctly")
    public void checkBlogButtonTest() {
        var dataloreLandingPage = new DataloreLandingPage(driver);
        dataloreLandingPage.clickOnBlogButton();
        dataloreLandingPage.switchToOpenedTab(driver);
        dataloreLandingPage.checkBlogUrl(driver);
        dataloreLandingPage.checkBlogTabTitle(driver);
    }

    @Test
    @DisplayName("Check that email for forgot password sent")
    public void checkForgotEmailSendingTest() {
        var dataloreLandingPage = new DataloreLandingPage(driver);
        var email = faker.internet().emailAddress();
        dataloreLandingPage.inputEmail(email);
        dataloreLandingPage.clickOnForgotPasswordButton();
        dataloreLandingPage.waitEmailSent(wait);
        dataloreLandingPage.checkForgotPasswordEmailSentText(email);
    }

    @Test
    @DisplayName("Check login positive scenario works correctly")
    public void checkPositiveLoginTest() {
        var dataloreLandingPage = new DataloreLandingPage(driver);
        dataloreLandingPage.inputEmail("1nrsmsf@gmail.com");
        dataloreLandingPage.inputPassword(
                dataloreLandingPage.decodePassword("SCQyYzUlY3dkamI=")
        );
        dataloreLandingPage.clickOnLogInButton();
        dataloreLandingPage.waitLoggedPage(wait);
        dataloreLandingPage.checkAfterLoginUrl(driver);
    }

    @Test
    @DisplayName("Check login negative scenario works correctly")
    public void checkNegativeLoginTest() {
        var dataloreLandingPage = new DataloreLandingPage(driver);
        dataloreLandingPage.inputEmail(faker.internet().emailAddress());
        dataloreLandingPage.inputPassword(faker.internet().password());
        dataloreLandingPage.clickOnLogInButton();
        dataloreLandingPage.waitWrongPasswordAlert(wait);
        dataloreLandingPage.checkWrongPasswordAlertText();
    }

}
