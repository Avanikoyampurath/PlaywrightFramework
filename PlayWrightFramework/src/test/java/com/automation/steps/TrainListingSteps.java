package com.automation.steps;

import com.automation.pages.TrainListingPage;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.assertions.LocatorAssertions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TrainListingSteps {

    TrainListingPage trainListingPage = new TrainListingPage();

    @Then("verify train list is displayed")
    public void verify_train_list_is_displayed() {
        assertThat(trainListingPage.getFirstTrain()).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(30000));
        trainListingPage.isTrainListingDisplayed();
    }


    @When("user click on sort by name A to Z")
    public void userClickOnSortByNameAToZ() {
        trainListingPage.sortByNameAtoZ();
    }

    @Then("verify sort by name A to Z")
    public void verifySortByNameAToZ() {
        trainListingPage.isSortingByNameAToZDisplayed();
    }
}
