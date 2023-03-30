package com.jetbrains.ui.pages;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

import io.qameta.allure.Step;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// page_url = https://k8s.stable.on-premise.datalore.io/
@SuppressWarnings("UnusedReturnValue")
public class DataloreLandingPage {
  final String loggedPageButtonPath =
      "//div[@class='button-group list-button type--primary ']/button[text() = 'New notebook']";
  final String emailSentMessagePath =
      "//div[@class = ' login-inputs']/following-sibling::div[contains(text(), 'Check your email')]";
  final String wrongPasswordAlertPath =
      "//div[@class='alert_message alert_message-error']/div[@class='alert_content']";

  // Web elements

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

  @FindBy(xpath = "//div[@data-test = 'error-message']/span[contains(text(), 'Email is required')]")
  private WebElement emailRequiredMessage;

  @FindBy(xpath = "//div[@data-test = 'error-message']/span[contains(text(), 'Email is invalid')]")
  private WebElement emailInvalidMessage;

  @FindBy(xpath = "//div[@data-test = 'error-message']/span[contains(text(), 'Password is required')]")
  private WebElement passwordInvalidMessage;

  @FindBy(xpath = emailSentMessagePath)
  private WebElement forgotPasswordEmailSentText;

  @FindBy(xpath = "//div[@class='alert_message alert_message-error']/div[@class='alert_content']")
  private WebElement wrongPasswordAlertText;

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

  // Actions
  @Step("Click on documentation button")
  public DataloreLandingPage clickOnDocumentationButton() {
    documentationButton.click();
    return this;
  }

  @Step("Click on community forum button")
  public DataloreLandingPage clickOnCommunityForumButton() {
    communityForumButton.click();
    return this;
  }

  @Step("Click on blog button")
  public DataloreLandingPage clickOnBlogButton() {
    blogButton.click();
    return this;
  }

  @Step("Click on create an account button")
  public DataloreLandingPage clickOnCreateAnAccountButton() {
    createAnAccountButton.click();
    return this;
  }

  @Step("Click on show password text button")
  public DataloreLandingPage clickOnShowPasswordButton() {
    passwordInputShowButton.click();
    return this;
  }

  @Step("Click on log in button")
  public DataloreLandingPage clickOnLogInButton() {
    logInButton.click();
    return this;
  }

  @Step("Click on forgot password button")
  public DataloreLandingPage clickOnForgotPasswordButton() {
    forgotPasswordButton.click();
    return this;
  }

  @Step("Write text in password input")
  public DataloreLandingPage inputPassword(String pass) {
    passwordInput.sendKeys(pass);
    return this;
  }

  @Step("Write text in email input")
  public DataloreLandingPage inputEmail(String pass) {
    emailInput.sendKeys(pass);
    return this;
  }

  @Step("Wait till button with text New notebook shows")
  public DataloreLandingPage waitLoggedPage(@NotNull WebDriverWait wait) {
    WebElement element =
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loggedPageButtonPath)));
    assertThat(element.isDisplayed(), equalTo(true));
    return this;
  }

  @Step("Wait till text that email sent shows")
  public DataloreLandingPage waitEmailSent(@NotNull WebDriverWait wait) {
    WebElement element =
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(emailSentMessagePath)));
    assertThat(element.isDisplayed(), equalTo(true));
    return this;
  }

  @Step("Wait till alert with incorrect email/password shows")
  public DataloreLandingPage waitWrongPasswordAlert(@NotNull WebDriverWait wait) {
    WebElement element =
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(wrongPasswordAlertPath)));
    assertThat(element.isDisplayed(), equalTo(true));
    return this;
  }

  @Step("Switch to opened tab")
  public DataloreLandingPage switchToOpenedTab(@NotNull WebDriver driver) {
    String currentWindowHandle = driver.getWindowHandle();
    Set<String> allWindowHandles = driver.getWindowHandles();

    for (String windowHandle : allWindowHandles) {
      if (!windowHandle.equals(currentWindowHandle)) {
        driver.switchTo().window(windowHandle);
        break;
      }
    }
    return this;
  }

  // Checks
  @Step("Check that URL of page is correct")
  public DataloreLandingPage checkElementUrl(@NotNull WebDriver driver, @NotNull String url) {
    assertThat("URL is not correct", driver.getCurrentUrl(), equalTo(url));
    return this;
  }

  @Step("Check tab title is correct")
  public DataloreLandingPage checkTabTitleText(@NotNull WebDriver driver, @NotNull String title) {
    assertThat("Tab title is not correct", driver.getTitle(), equalTo(title));
    return this;
  }

  @Step("Check that email address of support is correct")
  public DataloreLandingPage checkSupportEmailAddress() {
    assertThat("Email is not correct",
        supportButton.getAttribute("href"),
        equalTo("mailto:contact@datalore.jetbrains.com"));
    return this;
  }

  @Step("Check that login in here button is displayed")
  public DataloreLandingPage checkLogInHereButtonDisplayed() {
    assertThat("Login in here button is not displayed", logInHereButton.isDisplayed(), equalTo(true));
    return this;
  }

  @Step("Check that Create account button is displayed")
  public DataloreLandingPage checkCreateAccountButtonDisplayed() {
    assertThat("Create account button is not displayed", createAccountButton.isDisplayed(), equalTo(true));
    return this;
  }

  @Step("Check that login header text is correct")
  public DataloreLandingPage checkLoginHeaderText(String text) {
    assertThat("Login header text is: " + loginBlockHeader.getText(), loginBlockHeader.getText(), equalTo(text));
    return this;
  }

  @Step("Check that sent email text is correct")
  public DataloreLandingPage checkForgotPasswordEmailSentText(String email) {
    assertThat(
        "Forgot password text is: " + forgotPasswordEmailSentText.getText(),
        forgotPasswordEmailSentText.getText(),
        equalTo("Check your email " + email + " for instructions."));
    return this;
  }

  @Step("Check that alert is correct")
  public DataloreLandingPage checkWrongPasswordAlertText() {
    assertThat(
        "Alert text is: " + wrongPasswordAlertText.getText(),
        wrongPasswordAlertText.getText(),
        equalTo("One or both of your email/password was incorrect"));
    return this;
  }

  @Step("Check that password input type is correct")
  public DataloreLandingPage checkPasswordInputType(String type) {
    var actualType = passwordInput.getAttribute("type");
    assertThat("Password input type is: " + actualType, actualType, equalTo(type));
    return this;
  }

  @Step("Check that Email is required message is displayed")
  public DataloreLandingPage checkEmailRequiredMessageDisplayed() {
    assertThat(
        "Email is required message is not displayed",
        emailRequiredMessage.isDisplayed(),
        equalTo(true));
    return this;
  }

  @Step("Check that Email is invalid message is displayed")
  public DataloreLandingPage checkEmailInvalidMessageDisplayed() {
    assertThat(
        "Email is invalid message is not displayed",
        emailInvalidMessage.isDisplayed(),
        equalTo(true));
    return this;
  }

  @Step("Check that Password is required message is displayed")
  public DataloreLandingPage checkPasswordRequiredMessageDisplayed() {
    assertThat(
        "Password is required message is not displayed",
        passwordInvalidMessage.isDisplayed(),
        equalTo(true));
    return this;
  }

  @Step("Check all elements is displayed")
  public DataloreLandingPage checkAllElementsDisplayed() {
    assertAll(
        "All elements should be displayed",
        () ->
            assertThat(
                "Login block header is not displayed",
                loginBlockHeader.isDisplayed(),
                equalTo(true)),
        () -> assertThat("Email input is not displayed", emailInput.isDisplayed(), equalTo(true)),
        () ->
            assertThat(
                "Password input is not displayed", passwordInput.isDisplayed(), equalTo(true)),
        () ->
            assertThat(
                "Password input show button is not displayed",
                passwordInputShowButton.isDisplayed(),
                equalTo(true)),
        () ->
            assertThat("Log in button is not displayed", logInButton.isDisplayed(), equalTo(true)),
        () ->
            assertThat(
                "Create an account button is not displayed",
                createAnAccountButton.isDisplayed(),
                equalTo(true)),
        () ->
            assertThat(
                "Forgot password button is not displayed",
                forgotPasswordButton.isDisplayed(),
                equalTo(true)),
        () ->
            assertThat(
                "Support button is not displayed", supportButton.isDisplayed(), equalTo(true)),
        () ->
            assertThat(
                "Documentation button is not displayed",
                documentationButton.isDisplayed(),
                equalTo(true)),
        () ->
            assertThat(
                "Community Forum button is not displayed",
                communityForumButton.isDisplayed(),
                equalTo(true)),
        () -> assertThat("Blog button is not displayed", blogButton.isDisplayed(), equalTo(true)));
    return this;
  }
}
