package com.epam.gura.pageobject;

import com.epam.gura.Setup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PricePage extends AbstractPage {

	@FindBy(xpath = "//input[@id='edit-name-1']")
	private WebElement searchField;

	@FindBy(xpath ="//tr[contains(@class,'price_table_tr')][1]//a[@class='description-link']")
	private WebElement descriptionOfFirstProduct;

    public PricePage(Setup setup) {
        super(setup);
    }

    public void performSerachFor(String productName){
        searchField.sendKeys(productName);
        searchField.submit();
    }

    public ProductDetailsPage openDescriptionOfFirstProduct(){
        descriptionOfFirstProduct.click();
        return new ProductDetailsPage(setup);
    }
}
