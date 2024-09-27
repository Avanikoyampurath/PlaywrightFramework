package com.automation;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class EbayActions {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        Page page = browser.newPage();
        page.navigate("https://www.ebay.com/");

        Locator searchInput = page.getByLabel("Search for anything");
        searchInput.pressSequentially("Laptop");

        Locator categorySelect = page.getByLabel("Select a category for search");
        categorySelect.selectOption("Books");

        Locator searchBtn = page.locator("#gh-btn");
        searchBtn.click();

        searchInput.clear();

        Locator advSearchOption = page.getByTitle("Advanced Search");
        advSearchOption.click();

        Locator keywordInput = page.locator("#_nkw");
        keywordInput.fill("java");

        Locator category = page.getByLabel("In this category");
        category.selectOption("Books & Magazines");

        Locator saleCheckBx = page.locator("[name='LH_SaleItems']");
        saleCheckBx.check();

        Locator conditionRadioBtn = page.locator("(//fieldset[contains(@class,'condition')]//input)[1]");
        conditionRadioBtn.check();

        Locator sortSelect = page.getByLabel("Sort By");
        sortSelect.selectOption(new SelectOption().setValue("15"));

        Locator advSearchBtn = page.getByText("Search").last();
        advSearchBtn.press("Enter");

        assertThat(page.getByText("Sale items").first()).isVisible();
        assertThat(page.getByText("Brand New").first()).isVisible();

        playwright.close();

    }

}
