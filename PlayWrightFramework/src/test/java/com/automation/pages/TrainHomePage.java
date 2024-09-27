package com.automation.pages;

import com.microsoft.playwright.Locator;

public class TrainHomePage extends BasePage{

    Locator trainMenuBtn;
    Locator searchTrainsOption;
    Locator fromCityInput;
    Locator toCityInput;
    Locator dateBtn;
    Locator monthElement;
    Locator nextMonthBtn;
    Locator trainSearchBtn;

    public TrainHomePage(){
        trainMenuBtn=page.locator(".trains.mainMenu");
        searchTrainsOption=page.locator("#lbl1");
        fromCityInput=page.locator("#txtfromcity");
        toCityInput=page.locator("#txtdesticity");
        dateBtn=page.locator("#txtDate");
        monthElement=page.locator(".ui-datepicker-month").first();
        nextMonthBtn=page.locator("//span[text()='Next']");
        trainSearchBtn=page.locator("//input[@value='Search']");
    }

    public void openWebsite(String url) {
        page.navigate(url);
    }

    public void clickOnTrainMenu() {
        trainMenuBtn.click();
    }

    public void enterSourceStation(String fromStation) {
        fromCityInput.fill(fromStation);
        Locator fromCity=page.locator(String.format("//li//div[contains(text(),'%s')]", fromStation)).first();
        fromCity.click();
    }

    public void enterDestinationStation(String toStation) {
        toCityInput.fill(toStation);
        Locator toCity=page.locator(String.format("//li//div[contains(text(),'%s')]", toStation)).first();
        toCity.click();
    }


    public void enterDepartDate(String date) {
        dateBtn.click();
        String day = date.substring(0, date.indexOf(" "));
        String month = date.substring(date.indexOf(" ") + 1, date.lastIndexOf(" "));
        while (!monthElement.innerText().contains(month)) {
            nextMonthBtn.click();
        }
        String dayPath = "//div[contains(@class,'corner-left')]/..//a[text()='%s']";
        Locator dayElement = page.locator((String.format(dayPath, day)));
        dayElement.click();
    }

    public void clickOnTrainSearchBtn() {
        trainSearchBtn.click();
    }

    public Locator getSearchTrainsBtn() {
        return searchTrainsOption;
    }

    public Locator isHomePageDisplayed() {
        return trainMenuBtn;
    }
}
