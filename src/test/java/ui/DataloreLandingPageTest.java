package ui;

import com.github.javafaker.Faker;
import com.jetbrains.DataloreLandingPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("UI Tests for Datalore landing page")
public class DataloreLandingPageTest {

    WebDriver driver = new ChromeDriver();

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
    public void checkAllElementsDisplayedTest(){
        var dataloreLandingPage = new DataloreLandingPage(driver);
        dataloreLandingPage.checkAllElementsDisplayed();
    }


    @Test
    @DisplayName("Check password show button works correctly")
    public void checkPasswordShowButtonTest(){
        var dataloreLandingPage = new DataloreLandingPage(driver);
        var faker = new Faker();
        dataloreLandingPage.inputPassword(faker.name().firstName());
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
    @DisplayName("Check that documentation button")
    public void documentationButtonCheckTest() {
        var dataloreLandingPage = new DataloreLandingPage(driver);
        dataloreLandingPage.clickOnDocumentationButton();
        String currentWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();

        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        assertThat(driver.getCurrentUrl(),
                equalTo("https://www.jetbrains.com/help/datalore/datalore-quickstart.html"));

        assertThat(driver.getTitle(),
                equalTo("Quick start tutorial | Datalore Documentation"));
    }

    @Test
    @DisplayName("Check that forum button exists")
    public void forumCheckTest() throws InterruptedException {
        var dataloreLandingPage = new DataloreLandingPage(driver);
        dataloreLandingPage.clickOnCommunityForumButton();
        Thread.sleep(5000);
    }

    @Test
    @DisplayName("Check that blog button exists")
    public void blogCheckTest() throws InterruptedException {
        var dataloreLandingPage = new DataloreLandingPage(driver);
        dataloreLandingPage.clickOnBlogButton();
        Thread.sleep(5000);
    }
}
