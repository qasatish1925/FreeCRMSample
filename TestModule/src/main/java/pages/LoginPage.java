package pages;

import Base.TestBase;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends TestBase
{
    @FindBy(xpath = "//input[@placeholder='Email']")
    WebElement emailInputField;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement passwordInputField;

    @FindBy(xpath = "//div[text()='Login']")
    WebElement loginButton;

    @FindBy(xpath = "//b[text()='Deepak Automation']")
    WebElement loggedInUsername;

    @FindBy(xpath = "//a[contains(text(),'Forgot your password?')]")
    WebElement forgotPasswordButton;

    @FindBy(xpath="//input[@placeholder='Email address']")
    WebElement enterEmailForForgotPassword;

    @FindBy(xpath = "//button[contains(text(),'Reset password')]")
    WebElement resetPasswordButtonForForgotPassword;

    @FindBy(xpath = "//div[contains(text(),'Thank you. You will receive an email with a password reset link if this account is registered.')]")
    WebElement verifyForgotPasswordMessage;

    @FindBy(xpath = "//h1[text()='Reset your password']/following-sibling::a[contains(text(),'https://register.cogmento.com/password/reset/')]")
    WebElement resetPasswordURL;

    @FindBy(xpath = "//div[contains(text(),'You have successfully changed your password')]")
    WebElement successfulPasswordRestMessage;

    @FindBy(xpath = "//input[contains(@placeholder,'Enter a new password')]")
    WebElement enterNewPasswordForRest;

    @FindBy(xpath = "//input[@placeholder='Confirm your password']")
    WebElement confirmPasswordForRest;

    @FindBy(xpath = "//button[contains(text(),'Reset password')]")
    WebElement clickOnRestPassword;


    public LoginPage()
    {
        PageFactory.initElements(driver, this);
    }

    @Step("Enter username")
    public void enterUsername(String username)  {
        emailInputField.click();
        emailInputField.sendKeys(username);
    }

    @Step("Enter password")
    public void enterPassword(String password)
    {
        passwordInputField.sendKeys(password);
    }

    @Step("Click on Login button")
    public void clickOnLoginButton()
    {
        loginButton.click();
    }

    @Step("Verify the name of logged in user from the dashboard")
    public String getUsernameFromDashboard()
    {
        ut.explicitWait(loggedInUsername, 10);
        return loggedInUsername.getText();
    }

    @Step("Click on forgot you password button")
    public void clickOnForgotYourPassword()
    {
        forgotPasswordButton.click();
    }

    @Step("Enter email for forgot password")
    public void enterEmailForForgotPassword(String email)
    {
        enterEmailForForgotPassword.sendKeys(email);
    }

    @Step("Click on Rest password button for forgot pasword")
    public void clickOnResetPassword()
    {
        resetPasswordButtonForForgotPassword.click();
    }

    @Step("Verify forgot password successful message")
    public String verifyForgotPasswordSuccessfulMessage()
    {
        return verifyForgotPasswordMessage.getText();
    }

    @Step("Open received mail for rest password")
    public void OpenInboxMail(String fromEmail)
    {
        WebElement inboxMailForRestPassword = driver.findElement(By.xpath("//tr[1]//td[contains(text(),'"+fromEmail+"')]"));
        ut.explicitWait(inboxMailForRestPassword, 20);
        inboxMailForRestPassword.click();
    }

    @Step("Open reset password URL")
    public void openResetPasswordURL()
    {
        resetPasswordURL.click();
    }

    @Step("Enter a new password for reset the password")
    public void enterNewPassword(String pass)
    {
        enterNewPasswordForRest.sendKeys(pass);
    }

    @Step("Confirm the entered password for reset the password")
    public void enterConfirmPassword(String pass)
    {
        confirmPasswordForRest.sendKeys(pass);
    }

    @Step("Click on reset password button to set a new password")
    public void clickOnResetPasswordToSetPassword()
    {
        clickOnRestPassword.click();
    }

    @Step("Get successful password rest message")
    public String getSuccessfulPasswordResetMsg()
    {
       return successfulPasswordRestMessage.getText();
    }










}
