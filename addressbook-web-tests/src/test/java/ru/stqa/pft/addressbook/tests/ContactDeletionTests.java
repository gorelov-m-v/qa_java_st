package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testsContactDeletion() {
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isElementPresent(By.name("selected[]"))) {
            app.getContactHelper().createContact(new ContactData("Mikhail", "Junior",
                                                                 "Gorelov", "Junior",
                                                                 "89037776767", "testmail@testmail.com", "[none]"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.alertAccept();
    }
}