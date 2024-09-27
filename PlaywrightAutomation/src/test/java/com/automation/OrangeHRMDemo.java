package com.automation;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class OrangeHRMDemo {
    public static void main(String[] args) {
        Playwright playwright=Playwright.create();
        Browser browser=playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        BrowserContext context = browser.newContext();
        Page page= context.newPage();
        page.navigate("https://opensource-demo.orangehrmlive.com");

        Locator usernameInput= page.locator("//input[@name='username']");
        usernameInput.fill("Admin");

        Locator passwordInput= page.locator("//input[@name='password']");
        passwordInput.fill("admin123");

        Locator submitBtn= page.locator("//button[text()=' Login ']");
        submitBtn.click();

        Locator userDropDown= page.locator(".oxd-userdropdown");
        page.waitForLoadState(LoadState.LOAD);
        assertThat(userDropDown).isVisible();
        page.screenshot(new Page
                .ScreenshotOptions()
                .setPath(Paths.get("./target/screenshot.png"))
        );

        Page page1= context.newPage();
        page1.navigate("https://opensource-demo.orangehrmlive.com");
        assertThat(userDropDown).isVisible();

        BrowserContext browserContext= browser.newContext();
        Page newPage=browserContext.newPage();
        newPage.navigate("https://opensource-demo.orangehrmlive.com");
        Locator userInput=newPage.locator("//input[@name='username']");
        newPage.waitForLoadState(LoadState.LOAD);
        assertThat(userInput).isVisible();
        newPage.screenshot(new Page
                .ScreenshotOptions()
                .setMask(Collections.singletonList(userInput))
                .setMaskColor("Blue")
                .setPath(Paths.get("./target/ss.png"))
        );

    }
}
