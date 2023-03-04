package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testsContactDeletion() {

        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isElementPresent(By.name("selected[]"))) {
            app.getContactHelper().createContact(new ContactData("Mikhail", "Junior",
                                                                 "Gorelov", "Junior",
                                                                 "89037776767", "testmail@testmail.com",
                                                                 "[none]"));
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(before -1);
        app.getContactHelper().deleteSelectedContact();
        app.alertAccept();
        app.getContactHelper().waitingDeleteButton();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
    }
}
