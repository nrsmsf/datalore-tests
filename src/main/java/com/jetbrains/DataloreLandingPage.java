package com.jetbrains;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Base64;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

// page_url = https://k8s.stable.on-premise.datalore.io/
public class DataloreLandingPage {

    //Web elements

    @FindBy(xpath = "//div[@class='landing__login-form']/h1")
    private WebElement loginBlockHeader;

    @FindBy(xpath = "//input[@placeholder = 'Email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@placeholder = 'Password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@placeholder = 'Password']/following-sibling::*")
    private WebElement passwordInputShowButton;

    @FindBy(xpath = "//button[@data-test='button' and text() = 'Log in']")
    private WebElement logInButton;

    @FindBy(xpath = "//button[@data-test='button' and text() = 'Log in here!']")
    private WebElement logInHereButton;

    @FindBy(xpath = "//button[@data-test='button' and text() = 'Create account']")
    private WebElement createAccountButton;

    @FindBy(xpath = "//button[@data-test='button' and text() = 'Create an account']")
    private WebElement createAnAccountButton;

    @FindBy(xpath = "//button[@data-test='button' and text() = 'Forgot your password?']")
    private WebElement forgotPasswordButton;


    @FindBy(linkText = "Support")
    private WebElement supportButton;

    @FindBy(linkText = "Documentation")
    private WebElement documentationButton;

    @FindBy(linkText = "Community forum")
    private WebElement communityForumButton;

    @FindBy(linkText = "Blog")
    private WebElement blogButton;

    public DataloreLandingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //Actions
    public void clickOnDocumentationButton() {
        documentationButton.click();
    }

    public void clickOnCommunityForumButton() {
        communityForumButton.click();
    }

    public void clickOnBlogButton() {
        blogButton.click();
    }

    public void clickOnCreateAnAccountButton() {
        createAnAccountButton.click();
    }

    public void clickOnShowPasswordButton() {
        passwordInputShowButton.click();
    }
    public void clickOnLogInButton() {
        logInButton.click();
    }

    public void inputPassword(String pass) {
        passwordInput.sendKeys(pass);

    }
    public void inputEmail(String pass) {
        emailInput.sendKeys(pass);
    }

    public void switchToOpenedTab(@NotNull WebDriver driver) {
        String currentWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();

        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

public String decodePassword(String pass){
    var decodedBytes = Base64.getDecoder().decode(pass);
    return new String(decodedBytes);
}

    //Checks
    public void checkDocumentationUrl(@NotNull WebDriver driver) {
        assertThat(driver.getCurrentUrl(),
                equalTo("https://www.jetbrains.com/help/datalore/datalore-quickstart.html"));
    }
    public void checkAfterLoginUrl(@NotNull WebDriver driver) {
        assertThat(driver.getCurrentUrl(),
                equalTo("https://k8s.stable.on-premise.datalore.io/notebooks"));
    }

    public void checkDocumentationTabTitle(@NotNull WebDriver driver) {
        assertThat(driver.getTitle(),
                equalTo("Quick start tutorial | Datalore Documentation"));
    }
    public void checkForumUrl(@NotNull WebDriver driver) {
        assertThat(driver.getCurrentUrl(),
                equalTo("https://datalore-forum.jetbrains.com/"));
    }

    public void checkForumTabTitle(@NotNull WebDriver driver) {
        assertThat(driver.getTitle(),
                equalTo("Datalore Forum"));

    }    public void checkBlogUrl(@NotNull WebDriver driver) {
        assertThat(driver.getCurrentUrl(),
                equalTo("https://blog.jetbrains.com/datalore/"));
    }

    public void checkBlogTabTitle(@NotNull WebDriver driver) {
        assertThat(driver.getTitle(),
                equalTo("The JetBrains Datalore Blog : Collaborative data science platform for teams. | The JetBrains Blog"));
    }

    public void checkSupportEmailAddress() {
        assertThat("Email is not correct",
                supportButton.getAttribute("href"),
                equalTo("mailto:contact@datalore.jetbrains.com")
        );
    }

    public void checkLogInHereButtonDisplayed() {
        assertThat("Login in here button is not displayed", logInHereButton.isDisplayed(), equalTo(true));
    }

    public void checkCreateAccountButtonDisplayed() {
        assertThat("Create account button is not displayed", createAccountButton.isDisplayed(),
                equalTo(true));
    }

    public void checkLoginHeaderText(String text) {
        assertThat("Login header text is: " + loginBlockHeader.getText(),
                loginBlockHeader.getText(), equalTo(text));
    }

    public void checkPasswordInputType(String type) {
        var actualType = passwordInput.getAttribute("type");
        assertThat("Password input type is: " + actualType, actualType, equalTo(type));
    }

    public void checkAllElementsDisplayed() {
        assertThat("Login block header is not displayed", loginBlockHeader.isDisplayed(), equalTo(true));
        assertThat("Email input is not displayed", emailInput.isDisplayed(), equalTo(true));
        assertThat("Password input is not displayed", passwordInput.isDisplayed(), equalTo(true));
        assertThat("Password input show button is not displayed", passwordInputShowButton.isDisplayed(),
                equalTo(true));
        assertThat("Log in button is not displayed", logInButton.isDisplayed(), equalTo(true));
        assertThat("Create an account button is not displayed", createAnAccountButton.isDisplayed(),
                equalTo(true));
        assertThat("Forgot password button is not displayed", forgotPasswordButton.isDisplayed(),
                equalTo(true));
        assertThat("Support button is not displayed", supportButton.isDisplayed(), equalTo(true));
        assertThat("Documentation button is not displayed", documentationButton.isDisplayed(),
                equalTo(true));
        assertThat("Community Forum button is not displayed", communityForumButton.isDisplayed(),
                equalTo(true));
        assertThat("Blog button is not displayed", blogButton.isDisplayed(), equalTo(true));
    }

}