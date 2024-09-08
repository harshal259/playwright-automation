package stepdefinitions;

import com.microsoft.playwright.Page;
import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.LoginPage;
import utils.WebActions;

public class LoginSteps {
    LoginPage loginPage = new LoginPage(DriverFactory.getPage());

    @Given("^user navigates to \"([^\"]*)\"$")
    public void navigateToUrl(String url) {
        loginPage.navigateToUrl(url);
    }

    @When("^user enters \"([^\"]*)\" username$")
    public void enterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("^user enters \"([^\"]*)\" password$")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("^user clicks Search button$")
    public void clickSearch() {
        loginPage.clickLogin();
    }

    @When("^user clicks on \"([^\"]*)\" icon in main page")
    public void clickOnIcon(String iconName) {
        loginPage.clickOnIcon(iconName);
    }

    @Then("verify search results are displayed and contain {string}")
    public void verifySearchResultsDisplayed(String searchText) {
        Assert.assertTrue(loginPage.verifyResultsPage());
        String title = loginPage.getPageTitle();
        System.out.println("Page title is: " + DriverFactory.getPage().title());
        Assert.assertTrue(title.contains(searchText));
    }

//    @Then("^user verifies data as \"([^\"]*)\" in \"([^\"]*)\" row and \"([^\"]*)\" column from \"([^\"]*)\" sheet in \"([^\"]*)\" file")
//    public void clickOnIcon(String expectedValue, int rowNum, int colNum, String sheetName, String fileName) {
//        String actualValue = WebActions.getRowColValue(fileName, sheetName, rowNum, colNum);
//        Assert.assertEquals(expectedValue, actualValue);
//    }

    @When("^user searches for \"(.*)\"")
    public void enterSearchText(String searchText) throws InterruptedException {
        loginPage.searchFor(searchText);
        Thread.sleep(3000);
    }
}
