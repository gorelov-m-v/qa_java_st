package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreationTests() {
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("Mikhail", "Junior",
                                              "Gorelov", "Junior",
                                              "89037776767", "testmail@testmail.com",
                                              "[none]");
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        int max = 0;
        for(ContactData c : after) {
            if(c.getId() > max) {
                max = c.getId();
            }
        }
        contact.setId(max);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
