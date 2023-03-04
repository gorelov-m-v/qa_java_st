package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreationTests() {
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().createContact(new ContactData("Mikhail", "Junior",
                                                             "Gorelov", "Junior",
                                                             "89037776767", "testmail@testmail.com",
                                                             "[none]"));
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(before, after - 1);
    }

}
