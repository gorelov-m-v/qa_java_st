package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test(enabled = false)
    public void testsContactDeletion() {

        app.goTo().goToHomePage();
        if (! app.getContactHelper().isElementPresent(By.name("selected[]"))) {
            app.getContactHelper().createContact(new ContactData("Mikhail", "Junior",
                                                                 "Gorelov", "Junior",
                                                                 "89037776767", "testmail@testmail.com",
                                                                 "[none]"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() -1);
        app.getContactHelper().deleteSelectedContact();
        app.alertAccept();
        app.getContactHelper().waitingDeleteButton();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
