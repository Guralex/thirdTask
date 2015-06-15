package com.epam.gura.pageobject;

import com.epam.gura.Setup;
import com.epam.gura.helpers.Waiter;
import org.openqa.selenium.support.PageFactory;


public abstract class AbstractPage {
    protected Setup setup;
    protected Waiter wait = new Waiter();

    public AbstractPage(Setup setup) {
        this.setup = setup;
        PageFactory.initElements(setup.getDriver(), this);
    }
}
