package com.automation;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

public class EbayDemo {
    public static void main(String[] args) {
        Playwright playwright=Playwright.create();
        Browser browser=playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page= browser.newPage();
        page.navigate("https://www.ebay.com/");

        Locator advSearchBtn= page.locator("#gh-as-a");
        assert(advSearchBtn.isVisible());
        advSearchBtn.click();

        Locator keywordInput= page.locator("#_nkw");
        assert(keywordInput.isVisible());
        keywordInput.fill("Java");

        Locator selectDropdown=page.locator("//select[@aria-label='In this category']");
        selectDropdown.selectOption(new SelectOption().setValue("267"));

        Locator minPrice=page.locator("//input[contains(@aria-label,'minimum price')]");
        minPrice.fill("0");

        Locator maxPrice=page.locator("//input[contains(@aria-label,'maximum price')]");
        maxPrice.fill("100");

        Locator condition=page.locator("//label[text()='New']");
        condition.click();

        Locator availableTo=page.locator("//label[text()='Available to ']");
        availableTo.click();

        Locator selectAvailableToLoc=page.locator("//select[@aria-label='Available to ']");
        selectAvailableToLoc.selectOption(new SelectOption().setLabel("India"));

        Locator searchBtn=page.locator("//div[contains(@class,'adv-form')]/button[text()='Search']");
        searchBtn.click();

        Locator bookTitle=page.locator("//ul/li[@data-viewport]//span[@role='heading']").first();
        System.out.println(bookTitle.innerText());

//      new tab
        Page newPage=page.waitForPopup(bookTitle::click);
        Locator price=newPage.locator("//div[@class='x-price-primary']/span");
        System.out.println(price.innerText());
        newPage.close();

        playwright.close();
    }
}
