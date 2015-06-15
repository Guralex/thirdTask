package com.epam.gura.pageobject;

import com.epam.gura.Setup;
import com.epam.gura.helpers.HelperUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ComparePage extends AbstractPage {
 
    @FindBy(xpath = "//table[@class='compare']//tr/td[not(@colspan)][1]")
    private List<WebElement> descriptionFields;

    @FindBy(xpath ="//table[@class='compare']//tr/td[2]")
    private List<WebElement> prod1Description;

    @FindBy(xpath ="//table[@class='compare']//tr/td[3]")
    private List<WebElement> prod2Description;

    public ComparePage(Setup setup) {
        super(setup);
    }

    public boolean verifyCompareOfProducts(Map<String, String> prod1, Map<String, String> prod2) {
        wait.waitVisibilityOfAll(setup, prod1Description);
        wait.waitVisibilityOfAll(setup, prod2Description);
        wait.waitVisibilityOfAll(setup, descriptionFields);

        Map<String, String> prod1DescriptionMap = new HashMap<String, String>();
        Map<String, String> prod2DescriptionMap = new HashMap<String, String>();

        for (int i = 0; i < descriptionFields.size(); i++) {
            prod1DescriptionMap.put(descriptionFields.get(i).getText(), prod1Description.get(i).getText());
            prod2DescriptionMap.put(descriptionFields.get(i).getText(), prod2Description.get(i).getText());
            String currentFieldProd1;
            String currentFieldProd2;

            if (prod1.containsKey(descriptionFields.get(i).getText())) {
                String fieldOnPDP = prod1.get(descriptionFields.get(i).getText().replace("  "," "));
                currentFieldProd1 = prod1DescriptionMap.get(descriptionFields.get(i).getText().replace("  "," "));
                if (!fieldOnPDP.equals(currentFieldProd1)) {
                    return false;
                }

            }
            if (prod2.containsKey(descriptionFields.get(i).getText())) {
                String fieldOnPDP = prod2.get(descriptionFields.get(i).getText()).replace("  "," ");
                currentFieldProd2 = prod2DescriptionMap.get(descriptionFields.get(i).getText()).replace("  "," ");
                if (!fieldOnPDP.equals(currentFieldProd2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean verifyColorOfDifferentValues() {
        wait.waitVisibilityOfAll(setup, prod1Description);
        wait.waitVisibilityOfAll(setup, prod2Description);
        wait.waitVisibilityOfAll(setup, descriptionFields);

        Map<String, String> prod1DescriptionMap = new HashMap<String, String>();
        Map<String, String> prod2DescriptionMap = new HashMap<String, String>();

        for (int i = 0; i < descriptionFields.size(); i++) {
            prod1DescriptionMap.put(descriptionFields.get(i).getText(), prod1Description.get(i).getText());
            prod2DescriptionMap.put(descriptionFields.get(i).getText(), prod2Description.get(i).getText());

            String currentFieldProd1 = prod1DescriptionMap.get(descriptionFields.get(i).getText()).replace("  "," ");
            String currentFieldProd2 = prod2DescriptionMap.get(descriptionFields.get(i).getText()).replace("  "," ");

            if (!currentFieldProd1.equals(currentFieldProd2)) {
                String linePosition = String.valueOf(i + 1);
                String currentTR = String.format("(//table[@class='compare']//tr/td[not(@colspan)]/..)[%s]", linePosition);
                if (!setup.getDriver().findElement(By.xpath(currentTR)).getAttribute("class").equals("different")) {
                    return false;
                }
            }
        }

        return true;
    }
}
