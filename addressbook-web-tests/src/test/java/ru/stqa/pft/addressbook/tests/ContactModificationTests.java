package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.*;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (! app.contact().exists()) {
            app.contact().create(new ContactData("Mikhail", "Junior",
                    "Gorelov", "Junior",
                    "89037776767", "testmail@testmail.com",
                    "[none]"));
        }
    }

    @Test
    public void testsContactModification() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData(before.get(index).getId() ,"Mikhail2", "Junior2",
                                              "Gorelov2", "Junior2",
                                              "890377767672", "testmail2@testmail.com",
                                              "[none]");
        app.contact().modifyContact(contact, index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
