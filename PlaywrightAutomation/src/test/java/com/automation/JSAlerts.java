package com.automation;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class JSAlerts {
    public static void main(String[] args) {

        Playwright playwright=Playwright.create();
        Browser browser=playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page= browser.newPage();
        page.navigate("https://the-internet.herokuapp.com/javascript_alerts");

        page.onDialog(dialog -> {
            System.out.println("Dialog message: " + dialog.message());
            if (dialog.type().equals("alert")) {
                page.waitForTimeout(2000);
                dialog.accept();
            }
            else if (dialog.type().equals("confirm")) {
                page.waitForTimeout(2000);
                dialog.accept();
            }
            else if (dialog.type().equals("prompt")) {
                page.waitForTimeout(2000);
                dialog.accept("accepted");
            }

        });

        page.locator("//button[text()='Click for JS Confirm']").click();
        page.waitForTimeout(1000);
        assertThat(page.getByText("You clicked: Ok")).isVisible();

        page.locator("//button[text()='Click for JS Alert']").click();
        page.waitForTimeout(1000);
        assertThat(page.getByText("You successfully clicked an alert")).isVisible();


        page.locator("//button[text()='Click for JS Prompt']").click();
        page.waitForTimeout(1000);
        assertThat(page.getByText("You entered: accepted")).isVisible();

        playwright.close();
    }
}
