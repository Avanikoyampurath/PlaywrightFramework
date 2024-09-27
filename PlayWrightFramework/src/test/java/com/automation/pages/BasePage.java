package com.automation.pages;

import com.automation.utils.DriverManager;
import com.microsoft.playwright.Page;

public class BasePage {

    Page page;

    public BasePage(){
        this.page= DriverManager.getPage();
    }
}
