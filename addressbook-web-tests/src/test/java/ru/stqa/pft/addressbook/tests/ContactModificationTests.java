package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.*;

public class ContactModificationTests extends TestBase {

    @Test(enabled = false)
    public void testsContactModification() {
        app.goTo().goToHomePage();
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
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
