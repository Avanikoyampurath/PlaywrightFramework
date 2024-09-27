package com.automation.utils;
import com.microsoft.playwright.*;

import java.time.Duration;

public class DriverManager {
    static Page page;
    static Playwright playwright;
    static Browser browser;

    public static void createDriver() {
        playwright = Playwright.create();
        browser= playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        BrowserContext context = browser.newContext();
        page = context.newPage();
    }

    public static Page getPage() {
        return page;
    }
    public static void quit(){
        playwright.close();
    }
}
