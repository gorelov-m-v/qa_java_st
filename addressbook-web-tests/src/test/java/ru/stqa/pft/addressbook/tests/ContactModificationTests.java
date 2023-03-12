package ru.stqa.pft.addressbook.tests;

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
            app.contact().create(new ContactData().withFirstName("Mikhail").withMiddleName("Junior")
                                                  .withLastName("Gorelov").withNickName("Junior")
                                                  .withMobile("89037776767").withEmail("testmail@testmail.com")
                                                  .withGroup("[none]"));
        }
    }

    @Test
    public void testsContactModification() {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Mikhail").withMiddleName("Junior")
                                               .withLastName("Gorelov").withNickName("Junior")
                                               .withMobile("89037776767").withEmail("testmail@testmail.com")
                                               .withGroup("[none]");
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
