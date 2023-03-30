package ui;

import com.github.javafaker.Faker;
import com.jetbrains.ui.pagemanager.PageManager;
import com.jetbrains.utils.DataloreUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import java.time.Duration;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@Epic("UI")
@DisplayName("UI Tests for Datalore landing page")
public class DataloreLandingPageUITest {
    Faker faker = new Faker();
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    PageManager pm;

    @BeforeEach
    public void setUp() {
        driver.get("https://k8s.stable.on-premise.datalore.io/");
        pm = new PageManager(driver);
    }

    @AfterEach
    public void turnOff() {
        driver.quit();
    }

    @Test
    @DisplayName("Check if all elements displayed correctly")
    @Story("Datalore elements test")
    public void checkAllElementsDisplayedTest() {
        pm.dataloreLanding().checkAllElementsDisplayed();
    }

    @Test
    @DisplayName("Check password show button works correctly")
    @Story("Datalore elements test")
    public void checkPasswordShowButtonTest() {
        pm.dataloreLanding()
                .inputPassword(faker.internet().password())
                .checkPasswordInputType("password")
                .clickOnShowPasswordButton()
                .checkPasswordInputType("text");
    }

    @Test
    @DisplayName("Check create an account button works correctly")
    @Story("Datalore elements test")
    public void checkCreateAccountButtonTest() {
        pm.dataloreLanding()
                .checkLoginHeaderText("Log in")
                .clickOnCreateAnAccountButton()
                .checkLoginHeaderText("Sign up")
                .checkLogInHereButtonDisplayed()
                .checkCreateAccountButtonDisplayed();
    }

    @Test
    @DisplayName("Check that email address of support is correct")
    @Story("Datalore elements test")
    public void checkEmailAddressTest() {
        pm.dataloreLanding().checkSupportEmailAddress();
    }

    @Test
    @DisplayName("Check that documentation opens correctly")
    @Story("Datalore elements test")
    public void checkDocumentationButtonTest() {
        final var documentationUrl = "https://www.jetbrains.com/help/datalore/datalore-quickstart.html";
        final var docTabTitle = "Quick start tutorial | Datalore Documentation";

        pm.dataloreLanding()
                .clickOnDocumentationButton()
                .switchToOpenedTab(driver)
                .checkElementUrl(driver, documentationUrl)
                .checkTabTitleText(driver, docTabTitle);
    }

    @Test
    @DisplayName("Check that community forum opens correctly")
    @Story("Datalore elements test")
    public void checkForumButtonTest() {
        final var forumUrl = "https://datalore-forum.jetbrains.com/";
        final var forumTabTitle = "Datalore Forum";

        pm.dataloreLanding()
                .clickOnCommunityForumButton()
                .switchToOpenedTab(driver)
                .checkElementUrl(driver, forumUrl)
                .checkTabTitleText(driver, forumTabTitle);
    }

    @Test
    @DisplayName("Check that blog opens correctly")
    @Story("Datalore elements test")
    public void checkBlogButtonTest() {
        final var blogUrl = "https://blog.jetbrains.com/datalore/";
        final var blogTabTitle =
                "The JetBrains Datalore Blog : Collaborative data science platform for teams. | The JetBrains Blog";

        pm.dataloreLanding()
                .clickOnBlogButton()
                .switchToOpenedTab(driver)
                .checkElementUrl(driver, blogUrl)
                .checkTabTitleText(driver, blogTabTitle);
    }

    @Test
    @DisplayName("Check that email for forgot password sent")
    @Story("Datalore login test")
    public void checkForgotEmailSendingTest() {
        var email = faker.internet().emailAddress();
        pm.dataloreLanding()
                .inputEmail(email)
                .clickOnForgotPasswordButton()
                .waitEmailSent(wait)
                .checkForgotPasswordEmailSentText(email);
    }

    @Test
    @DisplayName("Check email required message is displayed correctly")
    @Story("Datalore login test")
    public void checkEmailRequiredMessageTest() {
        pm.dataloreLanding()
                .clickOnLogInButton()
                .checkEmailRequiredMessageDisplayed();
    }

    @Test
    @DisplayName("Check password required message is displayed correctly")
    @Story("Datalore login test")
    public void checkPasswordRequiredMessageTest() {
        pm.dataloreLanding()
                .clickOnLogInButton()
                .checkPasswordRequiredMessageDisplayed();
    }

    @Test
    @DisplayName("Check email invalid message is displayed correctly")
    @Story("Datalore login test")
    public void checkEmailInvalidMessageTest() {
        pm.dataloreLanding()
                .inputEmail(RandomStringUtils.randomAlphanumeric(5)
                        + "@" + RandomStringUtils.randomAlphanumeric(5))
                .clickOnLogInButton()
                .checkEmailInvalidMessageDisplayed();
    }

    @Test
    @DisplayName("Check login positive scenario works correctly")
    @Story("Datalore login test")
    public void checkPositiveLoginTest() {
        final var afterLoginUrl = "https://k8s.stable.on-premise.datalore.io/notebooks";
        pm.dataloreLanding()
                .inputEmail("1nrsmsf@gmail.com")
                .inputPassword(DataloreUtils.decodePassword("SCQyYzUlY3dkamI="))
                .clickOnLogInButton()
                .waitLoggedPage(wait)
                .checkElementUrl(driver, afterLoginUrl);
    }

    @Test
    @DisplayName("Check login negative scenario works correctly")
    @Story("Datalore login test")
    public void checkNegativeLoginTest() {
        pm.dataloreLanding()
                .inputEmail(faker.internet().emailAddress())
                .inputPassword(faker.internet().password())
                .clickOnLogInButton()
                .waitWrongPasswordAlert(wait)
                .checkWrongPasswordAlertText();
    }
}
