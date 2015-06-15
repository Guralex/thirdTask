package com.epam.gura.pageobject;

import com.epam.gura.Setup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProductDetailsPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='panel corner item-stats']/div[@class='row']/span[1]")
    private List<WebElement> keysOfDescriptionFields;

	@FindBy(xpath = "//div[@class='panel corner item-stats']/div[@class='row']/span[2]")
	private List<WebElement> valuesOfDescriptionFields;

	@FindBy(xpath =  "//div[@id='page-breadcrumbs']/a[3]")
	private WebElement breadcrumbsToListPage;

    public ProductDetailsPage(Setup setup) {
        super(setup);
    }

    public Map<String,String> descriptionOfProduct(){
        Map<String, String> description = new HashMap<String,String>();
        wait.waitVisibilityOfAll(setup, keysOfDescriptionFields);
        String str1 = keysOfDescriptionFields.get(0).getText();
        wait.waitVisibilityOfAll(setup, valuesOfDescriptionFields);
        String str2 = valuesOfDescriptionFields.get(0).getText();
        for(int i = 0; i<keysOfDescriptionFields.size(); i++){
            description.put(keysOfDescriptionFields.get(i).getText(), valuesOfDescriptionFields.get(i).getText());
        }
        return description;
    }

    public ProductListPage goToListPage(){
        breadcrumbsToListPage.click();
        return new ProductListPage(setup);
    }

    public String configureDescriptionStringForConditioner(){
        StringBuilder builder = new StringBuilder();
        builder.append(valuesOfDescriptionFields.get(0).getText());
        builder.append(" установка; ");
        builder.append(valuesOfDescriptionFields.get(1).getText());
        builder.append("; ");
        builder.append("мощность охлаждения ");
        builder.append(valuesOfDescriptionFields.get(2).getText());
        builder.append("; ");
        builder.append("мощность обогрева ");
        builder.append(valuesOfDescriptionFields.get(3).getText());
        return builder.toString().replace("  ", " ").trim();
    }
}
