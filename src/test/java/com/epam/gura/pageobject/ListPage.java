package com.epam.gura.pageobject;

import com.epam.gura.Setup;
import com.epam.gura.helpers.HelperUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ListPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='order']/a[1]")
    private WebElement sortByPrice;

    @FindBy(xpath = "//div[@class='order']/a[2]")
    private WebElement sortByName;

    @FindBy(xpath = "//div[@class='name']/a")
    private List<WebElement> listOfProducts;

    @FindBy(xpath = "//div[@class='price']/strong")
    private List<WebElement> listOfPrices;

    @FindBy(xpath ="//span[@class='compare_add_link comparep cs']")
    private List<WebElement> listOfAddToCompareLinks;

    @FindBy(xpath = "//a[@class='head-compare-link']")
    private List<WebElement> listOfCompareLinks;

	@FindBy(xpath = "//div[@class='panel corner criteria']/div[@class='group'][1]/div[@class='is_empty_items']/a[2]")
	private WebElement minPriceFilter;

    @FindBy(xpath = "//div[@class='panel corner criteria']/div[@class='group'][2]/div[@class='is_empty_items']/a[2]")
    private WebElement maxPriceFilter;

	@FindBy(xpath = "//a[text()='Kenwood']")
	private WebElement brandFilter;

    @FindBy(xpath =  "//a[text()='Регулировка веса']")
    private WebElement weightRegulatorFilter;

    @FindBy(xpath = "//div[@class='item']//div[@class='description']")
    private List<WebElement> listOfDescriptions;

	@FindBy(xpath = "//div[@class='link']/a[text()='прайсы']")
	private WebElement pricesLink;


    public ListPage(Setup setup) {
        super(setup);
    }

    public ListPage clickOnSortByPrice(){
        sortByPrice.click();
        return this;
    }

    public ListPage clickOnSortByName(){
        sortByName.click();
        return this;
    }

    public boolean verifySortByProductName(){
        for(int i = 0; i<listOfProducts.size()-1; i++){
            String firstName = listOfProducts.get(i).getText();
            String secondName = listOfProducts.get(i+1).getText();
            if(firstName.compareTo(secondName)>0){
                return false;
            }
        }
        return true;
    }

    public boolean verifySortByPrice(){
        for(int i = 0; i<listOfPrices.size()-1; i++){
        	 
            double firstPrice = Double.parseDouble(listOfPrices.get(i).getText().replace("грн", "").replace(" ", ""));
            double secondPrice = Double.parseDouble(listOfPrices.get(i).getText().replace("грн", "").replace(" ", ""));
            if(Double.valueOf(firstPrice).compareTo(secondPrice)>0){
                return false;
            }
        }
        return true;
    }

    public ComparePage addtoCompare(){
        wait.waitVisibilityOfAll(setup, listOfAddToCompareLinks);
        listOfAddToCompareLinks.get(0).click();
        listOfAddToCompareLinks.get(1).click();
        wait.waitVisibilityOf(setup, listOfCompareLinks.get(0));
        listOfCompareLinks.get(0).click();
        return new ComparePage(setup);
    }

    public ProductDetailsPage openProduct(int index){
        listOfProducts.get(index).click();
        return new ProductDetailsPage(setup);
    }

    public PricePage openPrice(){
        pricesLink.click();
        return new PricePage(setup);
    }

    public ProductDetailsPage openProductInPrice(int index){
        String productName = HelperUtils.nameOfProduct(listOfProducts.get(index).getText());
        PricePage pricePage = openPrice();
        pricePage.performSerachFor(productName);
        return pricePage.openDescriptionOfFirstProduct();
    }

    public boolean setAndVerifyPriceFilter(){
        double min =  Double.parseDouble(minPriceFilter.getText());
        minPriceFilter.click();
        wait.waitVisibilityOf(setup, maxPriceFilter);
        double max =  Double.parseDouble(maxPriceFilter.getText());
        maxPriceFilter.click();
        for(int i = 0; i<listOfPrices.size(); i++){
            if( Double.parseDouble(listOfPrices.get(i).getText().replace("грн", "").replace(" ", ""))>=min &&  
            		Double.parseDouble(listOfPrices.get(i).getText().replace("грн", "").replace(" ", ""))<=max){
                continue;
            }
            else{
                return false;
            }
        }
        return true;
    }

    public boolean setAndVerifyBrandFilter(){
        brandFilter.click();
        for(int i = 0; i<listOfProducts.size(); i++){
            if(listOfProducts.get(i).getAttribute("innerHTML").contains(brandFilter.getAttribute("innerHTML"))){
                continue;
            }
            else{
                return false;
            }
        }
        return true;
    }

    public boolean setAndVerifyWeightRegulatorFilter(){
        weightRegulatorFilter.click();
        for(int i = 0; i<listOfDescriptions.size(); i++){
            String s1 = listOfDescriptions.get(i).getAttribute("innerHTML");
            String s2 = weightRegulatorFilter.getAttribute("innerHTML");
            if(listOfDescriptions.get(i).getAttribute("innerHTML").contains(weightRegulatorFilter.getAttribute("innerHTML"))){
                continue;
            }
            else{
                return false;
            }
        }
        return true;
    }

    public boolean informationEquals(){
        wait.waitVisibilityOfAll(setup, listOfDescriptions);
        for(int i = 0; i<5; i++){
            String descriptionOnListPage = listOfDescriptions.get(i).getAttribute("innerHTML");
            String productName = listOfProducts.get(i).getAttribute("innerHTML");
            ProductDetailsPage pdp = openProduct(i);
            String descriptionPDP = pdp.configureDescriptionStringForConditioner();
            if(!descriptionOnListPage.contains(descriptionPDP)){
                return false;
            }
            pdp.goToListPage();
        }
        return true;
    }

    public boolean informationEqualsWithPrice(){
        for(int i = 0; i<5; i++){
            String descriptionOnListPage = listOfDescriptions.get(i).getText();
            ProductDetailsPage pdp = openProductInPrice(i);
            String descriptionPDP = pdp.configureDescriptionStringForConditioner();
            if(!descriptionOnListPage.contains(descriptionPDP)){
                return false;
            }
            pdp.goToListPage();
        }
        return true;
    }
}
