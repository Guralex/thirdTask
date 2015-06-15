package com.epam.gura.pageobject;

import com.epam.gura.Setup;
import com.epam.gura.helpers.NamePattern;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductListPage extends AbstractPage {

    @FindBy(xpath = "//a[text()='Zelmer']")
    private WebElement brandFilter;

    @FindBy(xpath = "//a[text()='Регулировка веса']")
    private WebElement weightRegulatorFilter;
    
    @FindBy(xpath = "//div[@class='description']")
    private List<WebElement> listOfDescriptions;

    @FindBy(xpath = "//a[text()='прайсы']")
    private WebElement pricesLink;
    
    @FindBy(xpath = "//div[@class='order']/a[1]")
    private WebElement sortByPrice;

    @FindBy(xpath = "//div[@class='order']/a[2]")
    private WebElement sortByName;

    @FindBy(xpath = "//div[@class='name']/a")
    private List<WebElement> listOfProducts;

    @FindBy(xpath = "//div[@class='price']/strong")
    private List<WebElement> listOfPrices;

    @FindBy(xpath = "//span[@class='compare_add_link comparep cs']")
    private List<WebElement> listOfAddToCompareLinks;

    @FindBy(xpath = "//a[@class='head-compare-link']")
    private List<WebElement> listOfCompareLinks;

    @FindBy(xpath = "//div[@class='panel corner criteria']/div[@class='group'][1]/div[@class='is_empty_items']/a[2]")
    private WebElement minPriceFilter;

    @FindBy(xpath = "//div[@class='panel corner criteria']/div[@class='group'][2]/div[@class='is_empty_items']/a[2]")
    private WebElement maxPriceFilter;




    public ProductListPage(Setup setup) {
        super(setup);
    }

    public ProductListPage clickOnSortByPrice() {
        sortByPrice.click();
        return this;
    }

    public ProductListPage clickOnSortByName() {
        sortByName.click();
        return this;
    }

    public boolean verifySortByProductName() {
        for (int i = 0; i < listOfProducts.size() - 1; i++) {
            String firstName = listOfProducts.get(i).getText();
            String secondName = listOfProducts.get(i + 1).getText();
            if (firstName.compareTo(secondName) > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean verifySortByPrice() {
        for (int i = 0; i < listOfPrices.size() - 1; i++) {

            double firstPrice = Double.parseDouble(listOfPrices.get(i)
                    .getText().replace("грн", "").replace(" ", ""));
            double secondPrice = Double.parseDouble(listOfPrices.get(i)
                    .getText().replace("грн", "").replace(" ", ""));
            if (Double.valueOf(firstPrice).compareTo(secondPrice) > 0) {
                return false;
            }
        }
        return true;
    }

    public ProductComparePage addtoCompare() {
        wait.waitVisibilityOfAll(setup, listOfAddToCompareLinks);
        listOfAddToCompareLinks.get(0).click();
        listOfAddToCompareLinks.get(1).click();
        wait.waitVisibilityOf(setup, listOfCompareLinks.get(0));
        listOfCompareLinks.get(0).click();
        return new ProductComparePage(setup);
    }

    public ProductDetailsPage openProduct(int i) {
        listOfProducts.get(i).click();
        return new ProductDetailsPage(setup);
    }

    public ProductPricePage openPrice() {
        pricesLink.click();
        return new ProductPricePage(setup);
    }

    public ProductDetailsPage openProductInPrice(int index) {
        String productName = NamePattern.nameOfProduct(listOfProducts
                .get(index).getText());
        ProductPricePage pricePage = openPrice();
        pricePage.performSerachFor(productName);
        return pricePage.openDescriptionOfFirstProduct();
    }

    public boolean setAndVerifyPriceFilter() {
        double min = Double.parseDouble(minPriceFilter.getText());
        minPriceFilter.click();
        wait.waitVisibilityOf(setup, maxPriceFilter);
        double max = Double.parseDouble(maxPriceFilter.getText());
        maxPriceFilter.click();
        for (int i = 0; i < listOfPrices.size(); i++) {
            if (Double.parseDouble(listOfPrices.get(i).getText()
                    .replace("грн", "").replace(" ", "")) >= min
                    && Double.parseDouble(listOfPrices.get(i).getText()
                            .replace("грн", "").replace(" ", "")) <= max) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean setAndVerifyBrandFilter() {
        brandFilter.click();
        for (int i = 0; i < listOfProducts.size(); i++) {
           System.out.println( listOfProducts.get(i).getText());
            if (listOfProducts.get(i).getText().contains(brandFilter.getText())) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean setAndVerifyWeightRegulatorFilter() {
        weightRegulatorFilter.click();
        for (int i = 0; i < listOfDescriptions.size()-2; i++) {
                System.out.println(listOfDescriptions.get(i).getText()+"----"+weightRegulatorFilter.getText());
                if (listOfDescriptions.get(i).getText().contains(weightRegulatorFilter.getText())) {
                  
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean informationEqualsWithPrice() {
        for (int i = 0; i < 2; i++) {
            String descriptionOnListPage = listOfDescriptions.get(i).getText();
            ProductDetailsPage prod = openProductInPrice(i);
            String actualDescription = prod
                    .configureDescriptionStringForConditioner();
            if (!descriptionOnListPage.contains(actualDescription)) {
                return false;
            }
            prod.goToListPage();
        }
        return true;
    }
}
