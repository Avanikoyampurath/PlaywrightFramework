Feature: Validate sort functionality for train

  Background:
    Given user opens the website
    Then verify user is on home page
    When user click on train menu
    Then verify train home page is displayed
    When user select source station as "Trivandrum"
    And user select destination station as "Kochi"
    And user select depart date as "12 November 2024"
    And click on train search button
    Then verify train list is displayed

  Scenario: verify user can sort train by name ascending
    When user click on sort by name A to Z
    Then verify sort by name A to Z
