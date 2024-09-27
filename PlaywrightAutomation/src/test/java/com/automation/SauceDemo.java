package com.automation;

import com.microsoft.playwright.*;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SauceDemo {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://www.saucedemo.com");

        Locator usernameInput = page.locator("#user-name");
        Locator passwordInput = page.locator("#password");
        Locator loginBtn = page.locator("#login-button");

        usernameInput.fill("standard_user");
        passwordInput.fill("secret_sauce");
        loginBtn.click();

        Locator productTitle = page.locator("span.title");
        assertThat(productTitle).hasText("Products");

        Locator addToCartBtn = page.locator("#add-to-cart-sauce-labs-backpack");
        addToCartBtn.click();

        Locator cartBtn = page.locator("#shopping_cart_container");
        cartBtn.click();

        System.out.println(page.locator("div.inventory_item_name").innerText());
        System.out.println(page.locator("div.inventory_item_price").innerText());

        Locator checkoutBtn = page.locator("#checkout");
        checkoutBtn.click();

        Locator firstNameInput = page.locator("#first-name");
        Locator lastNameInput = page.locator("#last-name");
        Locator postalInput = page.locator("#postal-code");

        firstNameInput.fill("test");
        lastNameInput.fill("test");
        postalInput.fill("4785357");

        Locator continueBtn = page.locator("#continue");
        continueBtn.click();

        List<Locator> checkoutInfo = page.locator("//div[@class='summary_info']/div[not(@class='cart_footer')]").all();
        for (Locator info : checkoutInfo) {
            System.out.println(info.innerText());
        }

        Locator finishBtn = page.locator("#finish");
        finishBtn.click();

        Locator successMsg = page.locator("//h2");
        System.out.println(successMsg.innerText());

        playwright.close();
        System.out.println("execution done");

    }
}
