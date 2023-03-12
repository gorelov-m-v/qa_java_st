package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

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
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Mikhail").withMiddleName("Junior")
                                               .withLastName("Gorelov").withNickName("Junior")
                                               .withMobile("89037776767").withEmail("testmail@testmail.com")
                                               .withGroup("[none]");
        app.contact().modify(contact);

        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
