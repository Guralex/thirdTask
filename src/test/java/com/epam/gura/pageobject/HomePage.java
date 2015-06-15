package com.epam.gura.pageobject;

import com.epam.gura.Setup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;




public class HomePage extends AbstractPage {
  
    @FindBy(xpath = "//a[contains(text(),'Холодильники')]")
    private WebElement refrigerators;

    @FindBy(xpath = "//a[contains(text(),'Микроволновки')]")
    private WebElement microwaves;

    @FindBy(xpath = "//a[contains(text(),'Стиральные машины')]")
    private WebElement washingMachines;

    @FindBy(xpath =  "//a[contains(text(),'Хлебопечи')]")
    private WebElement bakers;
    
    @FindBy(xpath =  "(//a[contains(text(),'Кондиционеры')])[1]")
    private WebElement conditioners;

    public HomePage(Setup setup) {
        super(setup);
        setup.getDriver().get("http://pn.com.ua/");
    }

    public ListPage goToRefrigerators() {
    	refrigerators.click();
        return new ListPage(setup);
    }
    public ListPage goToMicrowawes() {
    	microwaves.click();
        return new ListPage(setup);
    }
    public ListPage goToWashingMashines() {
    	washingMachines.click();
        return new ListPage(setup);
    }
    public ListPage goToBakers() {
    	bakers.click();
        return new ListPage(setup);
    }
    public ListPage goToConditioners() {
    	conditioners.click();
        return new ListPage(setup);
    }
}
