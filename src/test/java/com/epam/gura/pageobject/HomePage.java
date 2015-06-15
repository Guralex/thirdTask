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

    public ProductListPage goToRefrigerators() {
    	refrigerators.click();
        return new ProductListPage(setup);
    }
    public ProductListPage goToMicrowawes() {
    	microwaves.click();
        return new ProductListPage(setup);
    }
    public ProductListPage goToWashingMashines() {
    	washingMachines.click();
        return new ProductListPage(setup);
    }
    public ProductListPage goToBakers() {
    	bakers.click();
        return new ProductListPage(setup);
    }
    public ProductListPage goToConditioners() {
    	conditioners.click();
        return new ProductListPage(setup);
    }
}
