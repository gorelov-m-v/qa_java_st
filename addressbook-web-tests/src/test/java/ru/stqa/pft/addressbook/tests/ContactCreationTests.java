package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreationTests() {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().createContact(new ContactData("Mikhail", "Junior",
                                                             "Gorelov", "Junior",
                                                             "89037776767", "testmail@testmail.com",
                                                             "[none]"));
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }

}
