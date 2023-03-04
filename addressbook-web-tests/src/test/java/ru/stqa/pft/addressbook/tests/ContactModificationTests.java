package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testsContactModification() {
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isElementPresent(By.name("selected[]"))) {
        app.getContactHelper().createContact(new ContactData("Mikhail", "Junior",
                                                             "Gorelov", "Junior",
                                                             "89037776767", "testmail@testmail.com",
                                                             "[none]"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().modifyContact(new ContactData("Mikhail2", "Junior2",
                                                             "Gorelov2", "Junior2",
                                                             "890377767672", "testmail2@testmail.com",
                                                             "[none]"), before.size() - 1);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
    }
}
