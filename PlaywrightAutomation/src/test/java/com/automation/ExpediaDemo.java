package com.automation;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

public class ExpediaDemo {
    public static void main(String[] args) {
        int childrenCount=2;
        int adultCount=3;
        String ageChild1="5";
        String ageChild2="6";
        String originCity="Hyderabad";
        String destinCity="Delhi";
        String startDate="22 November 2024";
        String endDate="29 November 2024";

        Playwright playwright=Playwright.create();
        Browser browser=playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page= browser.newPage();
        page.navigate("https://www.expedia.co.in/");

        Locator flightOption= page.locator("//span[text()='Flights']");
        flightOption.click();

        Locator leavingFromBtn= page.locator("//button[@aria-label='Leaving from']");
        leavingFromBtn.click();
        Locator originInput= page.locator("#origin_select");
        originInput.fill(originCity);
        Locator departCity= page.locator(String.format("//button[contains(@aria-label,'%s')]",originCity)).first();
        departCity.click();

        Locator goingToBtn= page.locator("//button[@aria-label='Going to']");
        goingToBtn.click();
        Locator destinationInput= page.locator("#destination_select");
        destinationInput.fill(destinCity);
        Locator destinationCity= page.locator(String.format("//button[contains(@aria-label,'%s')]",destinCity)).first();
        destinationCity.click();

        Locator dateBtn= page.locator("//button[contains(@aria-label,'Dates')]");
        dateBtn.click();

        selectDate(startDate,page);
        selectDate(endDate,page);

        Locator applyDateBtn= page.locator("//button[@data-stid='apply-date-selector']");
        applyDateBtn.click();

        Locator travellersBtn= page.locator("//button[@data-stid='open-room-picker']");
        travellersBtn.click();

        Locator adultIncBtn= page.locator("//input[@id='traveler_selector_adult_step_input']/following-sibling::button");

        for (int i=1;i<adultCount;i++){
            adultIncBtn.click();
        }

        Locator childIncBtn= page.locator("//input[@id='traveler_selector_children_step_input']/following-sibling::button");

        for (int i=1;i<adultCount;i++){
            childIncBtn.click();
        }

        Locator selectAge1= page.locator("#age-traveler_selector_children_age_selector-0");
        selectAge1.selectOption(new SelectOption().setValue(ageChild1));

        Locator selectAge2= page.locator("#age-traveler_selector_children_age_selector-1");
        selectAge2.selectOption(new SelectOption().setValue(ageChild2));

        Locator travellerDoneBtn= page.locator("#travelers_selector_done_button");
        travellerDoneBtn.click();

        Locator searchBtn= page.locator("#search_button");
        searchBtn.click();

    }
    public static void selectDate(String date,Page page){

        String day=date.substring(0,date.indexOf(" "));
        String month=date.substring(date.indexOf(" ")+1);

        Locator monthElement= page.locator("//div[contains(@class,'month-double-left')]/span");
        Locator nextMonth= page.locator("//button[contains(@data-stid,'calendar-navigation-controls-next')]");

        while (!monthElement.innerText().equals(month)){
            nextMonth.click();
        }
        Locator dayElement= page.locator(String.format("//div[contains(@class,'month-double-left')]//div[text()='%s']",day));
        dayElement.click();
    }
}
