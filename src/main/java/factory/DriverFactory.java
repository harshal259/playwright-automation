package factory;

import com.microsoft.playwright.*;
import utils.WebActions;

import java.awt.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverFactory {
    public Browser browser;
    public static BrowserContext context;
    public static Page page;

    public static ThreadLocal<Page> threadLocalDriver = new ThreadLocal<>(); //For Parallel execution
    public static ThreadLocal<BrowserContext> threadLocalContext = new ThreadLocal<>();

    //Launches Browser as set by user in config file
    public Page initDriver(String browserName) {
        BrowserType browserType = null;
        Map<String,String> env = new HashMap<>();

        boolean headless = Boolean.valueOf(System.getProperty("headless", "true"));
        env.put("PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD","1");

        switch (browserName) {
            case "firefox":
                browserType = Playwright.create(new Playwright.CreateOptions().setEnv(env)).firefox();
                browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
            case "chrome":
                browserType = Playwright.create(new Playwright.CreateOptions().setEnv(env)).chromium();
                browser = browserType.launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headless));
                break;
            case "webkit":
                browserType = Playwright.create(new Playwright.CreateOptions().setEnv(env)).webkit();
                browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(headless));
                break;
        }
        if (browserType == null) throw new IllegalArgumentException("Could not Launch Browser for type" + browserType);

        //Window Maximize logic
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        int width = (int) screenSize.getWidth();
//        int height = (int) screenSize.getHeight();

        if (Boolean.valueOf(System.getProperty("recordVideo")) == true)
//            context = browser.newContext(new Browser.NewContextOptions().setViewportSize(width,height).setRecordVideoDir(Paths.get("videos/")));
            context = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("videos/")));
        else
//            context = browser.newContext((new Browser.NewContextOptions().setViewportSize(width,height)));
            context = browser.newContext((new Browser.NewContextOptions()));

//        Make sure to close, so that videos are saved.
//        context.close();

        //Below line is used to start the trace file
        context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(false));
        page = context.newPage();
        threadLocalDriver.set(page);
        threadLocalContext.set(context);
        return page;
    }

    public static synchronized Page getPage() {
        return threadLocalDriver.get(); // Will return Initialized Thread Local Driver
    }

    public static synchronized BrowserContext getContext() {
        return threadLocalContext.get(); // Will return Initialized Thread Local Context
    }

}
