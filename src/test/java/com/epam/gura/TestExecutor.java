package com.epam.gura;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import com.epam.gura.pageobject.ProductComparePage;
import com.epam.gura.pageobject.HomePage;
import com.epam.gura.pageobject.ProductListPage;
import com.epam.gura.pageobject.ProductDetailsPage;

public class TestExecutor extends BaseTest {

    @Test
    public void priceFilterTest() {
        HomePage homePage = new HomePage(setup);
        ProductListPage listPage = homePage.goToWashingMashines();
        assertTrue("Products are not filtered by price",
                listPage.setAndVerifyPriceFilter());
    }

    @Test
    public void brandFilterTest() {
        HomePage homePage = new HomePage(setup);
        ProductListPage listPage = homePage.goToBakers();
        assertTrue("Products are not filtered by brand",
                listPage.setAndVerifyBrandFilter());
    }

   @Test
    public void weightRegulatorFilterTest() {
        HomePage homePage = new HomePage(setup);
        ProductListPage listPage = homePage.goToBakers();
        assertTrue("Products are not filtered by weight regulation",
                listPage.setAndVerifyWeightRegulatorFilter());
    }
    
   @Test
    public void sortByNameAndPriceTest() {
        HomePage homePage = new HomePage(setup);
        ProductListPage listPage = homePage.goToRefrigerators();
        listPage.clickOnSortByName();
        assertTrue("Products are not sorted by title ",
                listPage.verifySortByProductName());
        listPage.clickOnSortByPrice();
        assertTrue("Products are not sorted by price.",
                listPage.verifySortByPrice());
    }

    @Test
    public void compareTest() {
        
        HomePage homePage = new HomePage(setup);
        ProductListPage listPage = homePage.goToMicrowawes();
        ProductDetailsPage productPage = listPage.openProduct(0);
        Map<String, String> firstDescription = productPage
                .descriptionOfProduct();
        productPage.goToListPage();
        productPage = listPage.openProduct(1);
        Map<String, String> secondDescription = productPage
                .descriptionOfProduct();
        productPage.goToListPage();
        ProductComparePage comparePage = listPage.addtoCompare();
        assertTrue("Properties are not displayed on compare page",
                comparePage.verifyCompareOfProducts(firstDescription,
                        secondDescription));
        assertTrue("Different properties are not marked by color",
                comparePage.verifyColorOfDifferentValues());
    }

  

    @Test
    public void equalInfoTest() {
        HomePage homePage = new HomePage(setup);
        ProductListPage listPage = homePage.goToConditioners();
         assertTrue("Product description is not equal to priceinfo.",
                listPage.informationEqualsWithPrice());
    }
}
