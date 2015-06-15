package com.epam.gura.pageobject;

import com.epam.gura.Setup;
import com.epam.gura.helpers.WaiterHelper;
import org.openqa.selenium.support.PageFactory;


public abstract class AbstractPage {
    protected Setup setup;
    protected WaiterHelper wait = new WaiterHelper();

    public AbstractPage(Setup setup) {
        this.setup = setup;
        PageFactory.initElements(setup.getDriver(), this);
    }
}
