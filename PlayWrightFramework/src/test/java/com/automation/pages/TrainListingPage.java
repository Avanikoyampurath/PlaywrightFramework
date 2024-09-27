package com.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainListingPage extends BasePage {

        List<Locator>trainList;
        Locator firstTrain;
        Locator nameAscBtn;

    public TrainListingPage(){
        firstTrain = page.locator(".tr-no").first();
        nameAscBtn= page.locator("#a");
    }



    public void isTrainListingDisplayed() {
        trainList= page.locator(".tr-no").all();
        Locator load= page.locator("srchText");
        load.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
        System.out.println(trainList.size());
        Assert.assertFalse(trainList.isEmpty());
    }

    public Locator getFirstTrain(){
        return firstTrain;
    }

    public void sortByNameAtoZ() {
        nameAscBtn.click();
        Locator load= page.locator("#srtText");
        load.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
    }

    public void isSortingByNameAToZDisplayed() {
        List<String> trainNames = new ArrayList<>();
        List<Locator> nameElements = page.locator("//div[@class='tr-name t-ellipsis']").all();
        for (Locator name : nameElements) {
            trainNames.add(name.innerText());
        }

        List<String> namesCopy = new ArrayList<>(trainNames);
        Collections.sort(namesCopy);
        Assert.assertEquals(namesCopy, trainNames);
    }
}
