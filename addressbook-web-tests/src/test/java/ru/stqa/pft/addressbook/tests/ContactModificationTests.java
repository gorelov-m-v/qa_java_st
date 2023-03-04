package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

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
        ContactData contact = new ContactData(before.get(before.size() - 1).getId() ,"Mikhail2", "Junior2",
                                              "Gorelov2", "Junior2",
                                              "890377767672", "testmail2@testmail.com",
                                              "[none]");
        app.getContactHelper().modifyContact(contact, before.size() - 1);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
