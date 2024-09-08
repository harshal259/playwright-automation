package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.WebActions;

public class LoginPage {
    private Page page;
    private final Locator USERNAME_EDITBOX;
    private final Locator PASSWORD_EDITBOX;
    private final Locator LOGIN_BUTTON;
    private final Locator BOOKS_SEARCH_BOX;
    private final Locator GOOGLE_SEARCH_BOX;
    private final Locator SEARCH_RESULTS;

    public LoginPage(Page page) {
        this.page = page;
        this.USERNAME_EDITBOX = page.locator("#userName");
        this.PASSWORD_EDITBOX = page.locator("#password");
        this.LOGIN_BUTTON = page.locator("#login");
        this.BOOKS_SEARCH_BOX = page.getByPlaceholder("Type to search");
        this.GOOGLE_SEARCH_BOX = page.locator("#APjFqb");
        this.SEARCH_RESULTS = page.locator("#center_col");
    }

    public void navigateToUrl(String url) {
        this.page.navigate(WebActions.getProperty(url));
    }

    public void enterUsername(String username) {
        USERNAME_EDITBOX.fill(WebActions.getProperty(username));
    }

    public void enterPassword(String password) {
        PASSWORD_EDITBOX.fill(WebActions.decrypt(password));
    }

    public void clickLogin() {
        LOGIN_BUTTON.click();
    }

    public void clickOnIcon(String iconName) {
        this.page.getByText(iconName, new Page.GetByTextOptions().setExact(true)).click();  // Clicks on the Exact text
    }

    public boolean verifyProfilePage() {
        return WebActions.waitUntilElementDisplayed(this.BOOKS_SEARCH_BOX, 60);
    }

    public void searchFor(String searchText) {
        this.GOOGLE_SEARCH_BOX.fill(searchText);
        this.GOOGLE_SEARCH_BOX.press("Enter");
    }

    public boolean verifyResultsPage() {
        return WebActions.waitUntilElementDisplayed(this.SEARCH_RESULTS, 10);
    }

    public String getPageTitle() {
        return page.title();
    }



}
